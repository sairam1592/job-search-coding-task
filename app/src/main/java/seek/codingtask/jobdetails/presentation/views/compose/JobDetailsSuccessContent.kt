package seek.codingtask.jobdetails.presentation.views.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seek.android.core.presentation.ui.Spacer
import seek.braid.compose.components.*
import seek.braid.compose.theme.Icons
import seek.braid.compose.theme.Typographies
import seek.codingtask.jobdetails.domain.model.JobDetailsItem

@Composable
fun JobDetailsSuccessContent(data: JobDetailsItem) {
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
        Column(Modifier.padding(12.dp)) {
            Text(data.title, Typographies.TextLargeStrong)
            Text(data.advertiser, Typographies.TextStandard)

            Spacer(32.dp)

            Text(data.classification, Typographies.TextStandardStrong)
            Text(data.companyName, Typographies.TextStandardStrong)

            Spacer(32.dp)

            Text(data.descriptions, Typographies.TextStandard)

            data.bulletPoints.forEach {
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
