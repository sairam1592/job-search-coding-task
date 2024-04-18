package seek.codingtask.searchresults.presentation

import androidx.navigation.NavController
import com.seek.android.core.presentation.mvi.NavigationAction
import timber.log.Timber

sealed class SearchResultsNavAction : NavigationAction {
    data class OpenJobDetails(
        val jobId: String,
    ) : SearchResultsNavAction() {
        override fun executeNavigation(navController: NavController) {
            Timber.d("Opening job $jobId")
            // TODO
        }
    }
}
