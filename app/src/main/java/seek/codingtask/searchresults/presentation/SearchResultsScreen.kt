package seek.codingtask.searchresults.presentation

import androidx.compose.runtime.Composable
import com.seek.android.core.navigation.screen.extensions.screenDestinationWithParams
import com.seek.android.core.presentation.mvi.MviScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.Scope
import seek.codingtask.searchresults.presentation.views.SearchResultsErrorView
import seek.codingtask.searchresults.presentation.views.SearchResultsListView
import seek.codingtask.searchresults.presentation.views.SearchResultsLoadingView

class SearchResultsScreen : MviScreen<SearchResultsUiState, SearchResultsUiEvent, SearchResultsNavAction>() {
    companion object {
        val destination = screenDestinationWithParams<SearchResultsArgs>("search-results")
    }

    override val navigationDestinations = listOf(destination)

    @Composable
    override fun Render(
        state: SearchResultsUiState,
        emit: (SearchResultsUiEvent) -> Unit,
    ) {
        when (state) {
            is SearchResultsUiState.Loading ->
                SearchResultsLoadingView()

            is SearchResultsUiState.Results ->
                SearchResultsListView(
                    items = state.items,
                    onItemClicked = { emit(SearchResultsUiEvent.ItemPressed(it)) },
                )

            is SearchResultsUiState.Error ->
                SearchResultsErrorView(
                    errorReason = state.errorReason,
                    onTryAgain = { emit(SearchResultsUiEvent.Refresh) },
                )
        }
    }

    @Composable
    override fun resolveViewModel(scope: Scope): SearchResultsViewModel = koinViewModel(scope = scope)
}
