package seek.codingtask.searchform.presentation

import androidx.navigation.NavController
import com.seek.android.core.presentation.mvi.NavigationAction
import seek.codingtask.searchresults.presentation.SearchResultsArgs
import seek.codingtask.searchresults.presentation.SearchResultsScreen

sealed class SearchFormNavAction : NavigationAction {
    data class OpenSearchResults(
        val keywords: String,
    ) : SearchFormNavAction() {
        override fun executeNavigation(navController: NavController) {
            navController.navigate(
                SearchResultsScreen.destination.route(SearchResultsArgs(keywords)),
            )
        }
    }
}
