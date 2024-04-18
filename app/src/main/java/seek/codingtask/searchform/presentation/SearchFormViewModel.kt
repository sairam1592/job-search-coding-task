package seek.codingtask.searchform.presentation

import androidx.lifecycle.SavedStateHandle
import com.seek.android.core.presentation.flow.SavedStateFlow
import com.seek.android.core.presentation.mvi.MviViewModel
import seek.codingtask.R

class SearchFormViewModel(
    savedStateHandle: SavedStateHandle,
) : MviViewModel<SearchFormUiState, SearchFormUiEvent, SearchFormNavAction>() {
    override val _uiStateStream: SavedStateFlow<SearchFormUiState> =
        SavedStateFlow(
            savedStateHandle = savedStateHandle,
            key = "search-form-state",
            defaultValue = SearchFormUiState.Form(keywords = ""),
        )

    override fun process(event: SearchFormUiEvent) {
        when (event) {
            is SearchFormUiEvent.KeywordsChanged -> updateKeywords(event.newValue)
            is SearchFormUiEvent.SubmitPressed -> onSubmitPressed()
        }
    }

    private fun updateKeywords(keywords: String) {
        val currentState =
            _uiStateStream.value as? SearchFormUiState.Form
                ?: return

        if (currentState.keywords == keywords) {
            return
        }

        if (keywords.length > 50) {
            _uiStateStream.value =
                currentState.copy(
                    keywords = keywords.take(50),
                    keywordsError = R.string.search_form_error_max_chars,
                )
        } else {
            _uiStateStream.value =
                currentState.copy(
                    keywords = keywords,
                    keywordsError = null,
                )
        }
    }

    private fun onSubmitPressed() {
        val currentState =
            _uiStateStream.value as? SearchFormUiState.Form
                ?: return

        if (currentState.keywords.isBlank()) {
            _uiStateStream.value =
                currentState.copy(
                    keywordsError = R.string.search_form_error_blank,
                )
        } else {
            requestNavigation(SearchFormNavAction.OpenSearchResults(currentState.keywords))
        }
    }
}
