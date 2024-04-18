package seek.codingtask.searchresults.presentation

import com.seek.android.core.presentation.mvi.UiEvent
import seek.codingtask.searchresults.domain.SearchResultsItem

sealed class SearchResultsUiEvent : UiEvent {
    data class ItemPressed(
        val searchResultsItem: SearchResultsItem,
    ) : SearchResultsUiEvent()

    data object Refresh : SearchResultsUiEvent()
}
