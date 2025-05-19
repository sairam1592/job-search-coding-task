package seek.codingtask.searchresults.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.seek.android.core.common.errors.ErrorReason
import com.seek.android.core.presentation.extension.defaultBackAction
import com.seek.android.core.presentation.ui.SeekPreview
import com.seek.android.core.presentation.ui.ThemedPreviews
import com.seek.android.core.presentation.ui.error.ErrorFullscreen
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.Button
import seek.braid.compose.components.ButtonSize
import seek.braid.compose.components.TopNavigation
import seek.braid.compose.theme.Spacings
import seek.codingtask.R

@Composable
fun SearchResultsErrorView(
    errorReason: ErrorReason,
    onTryAgain: () -> Unit,
) {
    BraidScaffold(
        toolbar = {
            TopNavigation(
                title = stringResource(R.string.search_results_title),
                startAction = defaultBackAction(),
            )
        },
    ) { defaultPadding ->
        Column(
            Modifier
                .padding(defaultPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacings.large),
        ) {
            ErrorFullscreen(error = errorReason)
            Button(
                stringResource(R.string.search_results_try_again),
                size = ButtonSize.Small,
                onClick = onTryAgain,
            )
        }
    }
}

@ThemedPreviews
@Composable
private fun Preview() = SeekPreview {
    SearchResultsErrorView(ErrorReason.Errored(), {})
}
