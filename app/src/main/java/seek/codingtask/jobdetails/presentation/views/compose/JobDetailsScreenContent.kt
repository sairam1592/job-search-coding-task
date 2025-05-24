package seek.codingtask.jobdetails.presentation.views.compose

import androidx.compose.runtime.Composable
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.TopNavigation
import seek.codingtask.jobdetails.presentation.JobDetailsUIEvent
import seek.codingtask.jobdetails.presentation.JobDetailsUIState

@Composable
fun JobDetailsScreenContent(
    state: JobDetailsUIState,
    onEvent: (JobDetailsUIEvent) -> Unit
) {
    when (state) {
        is JobDetailsUIState.Loading -> {
            BraidScaffold(
                toolbar = {
                    TopNavigation(title = "Job Details")
                }
            ) {
                JobDetailsLoadingView()
            }
        }

        is JobDetailsUIState.Error -> {
            BraidScaffold(
                toolbar = {
                    TopNavigation(title = "Job Details")
                }
            ) {
                JobDetailsErrorView(
                    reason = state.reason,
                    onRetry = { onEvent(JobDetailsUIEvent.Retry) }
                )
            }
        }

        is JobDetailsUIState.Success -> {
            JobDetailsSuccessContent(data = state.data)
        }
    }
}
