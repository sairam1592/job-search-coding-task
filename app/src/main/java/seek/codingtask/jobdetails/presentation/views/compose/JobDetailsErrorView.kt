package seek.codingtask.jobdetails.presentation.views.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.seek.android.core.common.errors.ErrorReason
import com.seek.android.core.presentation.ui.SeekPreview
import com.seek.android.core.presentation.ui.ThemedPreviews
import com.seek.android.core.presentation.ui.error.ErrorFullscreen
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.Button
import seek.braid.compose.components.ButtonSize
import seek.braid.compose.components.TopNavigation
import seek.codingtask.R

@Composable
fun JobDetailsErrorView(
    reason: ErrorReason,
    onRetry: () -> Unit
) {
    BraidScaffold(
        toolbar = {
            TopNavigation(title = "Job Details")
        }
    ) { defaultPadding ->
        ErrorFullscreen(
            error = reason
        )

        Button(
            stringResource(R.string.search_results_try_again),
            size = ButtonSize.Small,
            onClick = onRetry,
        )
    }
}

@ThemedPreviews
@Composable
private fun PreviewErrorView() = SeekPreview {
    JobDetailsErrorView(
        reason = ErrorReason.Errored(),
        onRetry = {}
    )
}
