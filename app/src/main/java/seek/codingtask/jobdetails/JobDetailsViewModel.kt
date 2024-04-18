package seek.codingtask.jobdetails

import androidx.lifecycle.SavedStateHandle
import com.seek.android.core.presentation.flow.SavedStateFlow
import com.seek.android.core.presentation.mvi.MviViewModel
import com.seek.android.core.presentation.mvi.NavigationAction
import com.seek.android.core.presentation.mvi.UiEvent
import com.seek.android.core.presentation.mvi.UiState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JobDetailsViewModel(
    savedStateHandle: SavedStateHandle,
) : MviViewModel<UiState, UiEvent, NavigationAction>() {
    @Parcelize
    object Loading : UiState

    @Parcelize
    data class JobDetails(
        val advertiser: String,
        val bulletPoints: List<String>,
        val jobId: String,
        val title: String,
        val descriptions: String,
        val classification: String,
        val companyName: String
    ) : UiState

    val apiClient: JobDetailsApiClient
        get() {
            val retrofit =
                Retrofit.Builder()
                    .baseUrl("https://jobsearch-api.cloud.seek.com.au")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(JobDetailsApiClient::class.java)
        }

    init {
        GlobalScope.launch {
            val jobDetails = apiClient
                .getJobsList(JobDetailsScreen.destination.args(savedStateHandle).jobId)
                .body()!!.data.single()

            _uiStateStream.value = JobDetails(
                advertiser = jobDetails.advertiser.description,
                bulletPoints = jobDetails.bulletPoints,
                jobId = jobDetails.id,
                title = jobDetails.title,
                descriptions = jobDetails.teaser,
                classification = jobDetails.classifications.first().classification.description,
                companyName = jobDetails.companyName
            )
        }
    }

    override val _uiStateStream: SavedStateFlow<UiState> =
        SavedStateFlow(
            savedStateHandle = savedStateHandle,
            key = "job-details-state",
            defaultValue = Loading,
        )

    override fun process(event: UiEvent) {

    }
}
