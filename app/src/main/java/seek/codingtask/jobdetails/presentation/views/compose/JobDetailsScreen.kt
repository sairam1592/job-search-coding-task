package seek.codingtask.jobdetails.presentation.views.compose

import androidx.compose.runtime.Composable
import com.seek.android.core.navigation.screen.extensions.screenDestinationWithParams
import com.seek.android.core.presentation.mvi.MviScreen
import com.seek.android.core.presentation.mvi.NavigationAction
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.Scope
import seek.codingtask.jobdetails.JobDetailsArgs
import seek.codingtask.jobdetails.presentation.JobDetailsUIEvent
import seek.codingtask.jobdetails.presentation.JobDetailsUIState
import seek.codingtask.jobdetails.presentation.viewmodel.JobDetailsViewModel

class JobDetailsScreen : MviScreen<JobDetailsUIState, JobDetailsUIEvent, NavigationAction>() {
    companion object {
        val destination = screenDestinationWithParams<JobDetailsArgs>("job-details")
    }

    override val navigationDestinations = listOf(destination)

    @Composable
    override fun Render(
        state: JobDetailsUIState,
        emit: (JobDetailsUIEvent) -> Unit,
    ) {
        JobDetailsScreenContent(state = state, onEvent = emit)
    }

    @Composable
    override fun resolveViewModel(scope: Scope): JobDetailsViewModel = koinViewModel(scope = scope)
}
