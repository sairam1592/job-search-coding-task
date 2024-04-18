package seek.codingtask.searchform.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.seek.android.core.presentation.ui.SeekPreview
import com.seek.android.core.presentation.ui.Spacer
import com.seek.android.core.presentation.ui.ThemedPreviews
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.Button
import seek.braid.compose.components.ButtonTone
import seek.braid.compose.components.ButtonVariant
import seek.braid.compose.components.FieldTone
import seek.braid.compose.components.IconState
import seek.braid.compose.components.TextField
import seek.braid.compose.components.TopNavigation
import seek.braid.compose.theme.Icons
import seek.braid.compose.theme.Spacings
import seek.codingtask.R
import seek.codingtask.searchform.presentation.SearchFormUiEvent
import seek.codingtask.searchform.presentation.SearchFormUiState

@Composable
fun SearchFormView(
    state: SearchFormUiState.Form,
    emit: (SearchFormUiEvent) -> Unit,
) {
    BraidScaffold(
        toolbar = {
            TopNavigation(title = stringResource(R.string.app_name))
        },
    ) { defaultPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(defaultPadding)
                .padding(horizontal = Spacings.medium),
        ) {
            TextField(
                placeholder = stringResource(R.string.search_form_keywords),
                startIcon = Icons.Search(IconState.Inactive),
                value = state.keywords,
                onValueChanged = { value -> emit(SearchFormUiEvent.KeywordsChanged(value)) },
                tone = if (state.keywordsError == null) FieldTone.Neutral else FieldTone.Critical,
                message = state.keywordsError?.let { stringResource(id = it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )

            Box(modifier = Modifier.weight(1f))

            Button(
                variant = ButtonVariant.Standard,
                tone = ButtonTone.BrandAccent,
                text = stringResource(R.string.search_form_submit_button),
                onClick = { emit(SearchFormUiEvent.SubmitPressed) },
            )

            Spacer(size = Spacings.medium)
        }
    }
}

@ThemedPreviews
@Composable
private fun Preview() =
    SeekPreview {
        SearchFormView(SearchFormUiState.Form(""), {})
    }
