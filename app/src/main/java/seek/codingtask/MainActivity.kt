package seek.codingtask

import com.seek.android.core.presentation.mvi.MviActivity
import seek.codingtask.searchform.presentation.SearchFormScreen
import seek.codingtask.searchresults.presentation.SearchResultsScreen

class MainActivity : MviActivity() {
    override val screens = setOf(SearchFormScreen(), SearchResultsScreen())

    override val startDestination = SearchFormScreen.destination
}
