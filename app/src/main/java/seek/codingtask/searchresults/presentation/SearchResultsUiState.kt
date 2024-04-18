package seek.codingtask.searchresults.presentation

import com.seek.android.core.common.errors.ErrorReason
import com.seek.android.core.presentation.mvi.UiState
import kotlinx.parcelize.Parcelize
import seek.codingtask.searchresults.domain.SearchResultsItem

@Parcelize
sealed class SearchResultsUiState : UiState {
    data object Loading : SearchResultsUiState()

    data class Results(
        val items: List<SearchResultsItem>,
    ) : SearchResultsUiState()

    data class Error(
        val errorReason: ErrorReason,
    ) : SearchResultsUiState()
}
