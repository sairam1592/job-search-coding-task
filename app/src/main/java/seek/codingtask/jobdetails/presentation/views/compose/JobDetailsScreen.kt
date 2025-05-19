package seek.codingtask.jobdetails.presentation.views.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seek.android.core.navigation.screen.extensions.screenDestinationWithParams
import com.seek.android.core.presentation.mvi.MviScreen
import com.seek.android.core.presentation.mvi.NavigationAction
import com.seek.android.core.presentation.mvi.UiEvent
import com.seek.android.core.presentation.mvi.UiState
import com.seek.android.core.presentation.ui.Spacer
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.Scope
import seek.braid.compose.components.Alert
import seek.braid.compose.components.AlertNoticeTone
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.Text
import seek.braid.compose.components.TextAlertNoticeContent
import seek.braid.compose.components.TopNavigation
import seek.braid.compose.components.TopNavigationAction
import seek.braid.compose.theme.Icons
import seek.braid.compose.theme.Typographies
import seek.codingtask.jobdetails.JobDetailsArgs
import seek.codingtask.jobdetails.presentation.viewmodel.JobDetailsViewModel

class JobDetailsScreen : MviScreen<UiState, UiEvent, NavigationAction>() {
    companion object {
        val destination = screenDestinationWithParams<JobDetailsArgs>("job-details")
    }

    override val navigationDestinations = listOf(destination)

    @Composable
    override fun Render(
        state: UiState,
        emit: (UiEvent) -> Unit,
    ) {
        if (state is JobDetailsViewModel.JobDetails) {
            BraidScaffold(
                toolbar = {
                    TopNavigation(
                        title = "Job Details",
                        endActions = listOf(
                            TopNavigationAction.WithIcon(Icons.Share, null)
                        )
                    )
                }
            ) {
                Column(Modifier.Companion.padding(12.dp)) {
                    Text(state.title, Typographies.TextLargeStrong)
                    Text(state.advertiser, Typographies.TextStandard)

                    Spacer(32.dp)

                    Text(state.classification, Typographies.TextStandardStrong)
                    Text(state.companyName, Typographies.TextStandardStrong)

                    Spacer(32.dp)

                    Text(state.descriptions, Typographies.TextStandard)

                    state.bulletPoints.forEach {
                        Text(it, Typographies.TextStandard)
                    }

                    Spacer(32.dp)

                    Alert(tone = AlertNoticeTone.Caution) {
                        TextAlertNoticeContent(
                            tone = it,
                            text = "These are real job ads driven from the production search API. Please do not apply to them."
                        )
                    }
                }
            }
        }
    }

    @Composable
    override fun resolveViewModel(scope: Scope): JobDetailsViewModel {
        return koinViewModel(scope = scope)
    }
}