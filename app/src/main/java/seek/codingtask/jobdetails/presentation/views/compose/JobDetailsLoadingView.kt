package seek.codingtask.jobdetails.presentation.views.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seek.android.core.presentation.ui.Loading
import com.seek.android.core.presentation.ui.SeekPreview
import com.seek.android.core.presentation.ui.ThemedPreviews
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.TopNavigation

@Composable
fun JobDetailsLoadingView() {
    BraidScaffold(
        toolbar = {
            TopNavigation(title = "Job Details")
        }
    ) { defaultPadding ->
        Box(Modifier.padding(defaultPadding)) {
            Loading {}
        }
    }
}

@ThemedPreviews
@Composable
private fun PreviewLoadingView() = SeekPreview {
    JobDetailsLoadingView()
}
