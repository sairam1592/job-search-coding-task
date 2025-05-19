package seek.codingtask.searchresults.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.seek.android.core.presentation.extension.defaultBackAction
import com.seek.android.core.presentation.ui.Loading
import com.seek.android.core.presentation.ui.SeekPreview
import com.seek.android.core.presentation.ui.ThemedPreviews
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.TopNavigation
import seek.codingtask.R

@Composable
fun SearchResultsLoadingView() {
    BraidScaffold(
        toolbar = {
            TopNavigation(
                title = stringResource(R.string.search_results_title),
                startAction = defaultBackAction(),
            )
        },
    ) { defaultPadding ->
        Box(Modifier.padding(defaultPadding)) {
            Loading {
            }
        }
    }
}

@ThemedPreviews
@Composable
private fun Preview() = SeekPreview {
    SearchResultsLoadingView()
}
