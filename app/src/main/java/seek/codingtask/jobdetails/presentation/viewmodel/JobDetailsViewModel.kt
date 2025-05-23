package seek.codingtask.jobdetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.seek.android.core.presentation.exceptions.safeLaunch
import com.seek.android.core.presentation.flow.SavedStateFlow
import com.seek.android.core.presentation.mvi.MviViewModel
import com.seek.android.core.presentation.mvi.NavigationAction
import kotlinx.coroutines.flow.collectLatest
import seek.codingtask.jobdetails.domain.usecase.GetJobDetailsUseCase
import seek.codingtask.jobdetails.presentation.JobDetailsUIEvent
import seek.codingtask.jobdetails.presentation.JobDetailsUIState
import seek.codingtask.jobdetails.presentation.views.compose.JobDetailsScreen

class JobDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getJobDetailsUseCase: GetJobDetailsUseCase
) : MviViewModel<JobDetailsUIState, JobDetailsUIEvent, NavigationAction>() {

    override val _uiStateStream: SavedStateFlow<JobDetailsUIState> =
        SavedStateFlow(
            savedStateHandle = savedStateHandle,
            key = "job-details-state",
            defaultValue = JobDetailsUIState.Loading,
        )

    private val args = JobDetailsScreen.destination.args(savedStateHandle)

    init {
        fetchJobDetails()
    }

    private fun fetchJobDetails() {
        safeLaunch(
            {
                getJobDetailsUseCase(args.jobId).collectLatest { result ->

                    _uiStateStream.value = JobDetailsUIState.Success(result)
                }
            },
            errorProcessor = {
                _uiStateStream.value = JobDetailsUIState.Error(it.errorReason)
            }
        )
    }

    override fun process(event: JobDetailsUIEvent) {
        when (event) {
            JobDetailsUIEvent.Retry -> fetchJobDetails()
        }
    }
}
