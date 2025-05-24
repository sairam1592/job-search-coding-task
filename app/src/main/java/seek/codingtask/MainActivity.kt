package seek.codingtask

import com.seek.android.core.presentation.mvi.MviActivity
import seek.codingtask.jobdetails.presentation.views.compose.JobDetailsScreen
import seek.codingtask.searchform.presentation.SearchFormScreen
import seek.codingtask.searchresults.presentation.SearchResultsScreen

class MainActivity : MviActivity() {
    override val screens = setOf(SearchFormScreen(), SearchResultsScreen(), JobDetailsScreen())

    override val startDestination = SearchFormScreen.destination
}
