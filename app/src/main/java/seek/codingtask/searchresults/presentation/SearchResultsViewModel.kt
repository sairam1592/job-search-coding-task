package seek.codingtask.searchresults.presentation

import androidx.lifecycle.SavedStateHandle
import com.seek.android.core.presentation.exceptions.safeLaunch
import com.seek.android.core.presentation.flow.SavedStateFlow
import com.seek.android.core.presentation.mvi.MviViewModel
import kotlinx.coroutines.flow.collectLatest
import seek.codingtask.searchresults.domain.GetSearchResults

class SearchResultsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getSearchResults: GetSearchResults,
) : MviViewModel<SearchResultsUiState, SearchResultsUiEvent, SearchResultsNavAction>() {
    override val _uiStateStream: SavedStateFlow<SearchResultsUiState> =
        SavedStateFlow(
            savedStateHandle = savedStateHandle,
            key = "search-results-state",
            defaultValue = SearchResultsUiState.Loading,
        )

    private val args = SearchResultsScreen.destination.args(savedStateHandle)

    init {
        refreshSearchResults()
    }

    override fun process(event: SearchResultsUiEvent) {
        when (event) {
            is SearchResultsUiEvent.Refresh -> refreshSearchResults()
            is SearchResultsUiEvent.ItemPressed ->
                requestNavigation(
                    SearchResultsNavAction.OpenJobDetails(jobId = event.searchResultsItem.jobId),
                )
        }
    }

    private fun refreshSearchResults() {
        safeLaunch(
            {
                _uiStateStream.value = SearchResultsUiState.Loading
                getSearchResults.perform(args.keywords)
                    .collectLatest { data ->
                        _uiStateStream.value = SearchResultsUiState.Results(data)
                    }
            },
            errorProcessor = { error ->
                _uiStateStream.value = SearchResultsUiState.Error(error.errorReason)
            },
        )
    }
}
