package seek.codingtask.searchform.presentation

import androidx.compose.runtime.Composable
import com.seek.android.core.navigation.screen.extensions.screenDestination
import com.seek.android.core.presentation.mvi.MviScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.Scope
import seek.codingtask.searchform.presentation.views.SearchFormView

class SearchFormScreen : MviScreen<SearchFormUiState, SearchFormUiEvent, SearchFormNavAction>() {
    companion object {
        val destination = screenDestination("search-form")
    }

    override val navigationDestinations = listOf(destination)

    @Composable
    override fun Render(
        state: SearchFormUiState,
        emit: (SearchFormUiEvent) -> Unit,
    ) {
        when (state) {
            is SearchFormUiState.Form ->
                SearchFormView(state = state, emit = emit)
        }
    }

    @Composable
    override fun resolveViewModel(scope: Scope): SearchFormViewModel {
        return koinViewModel(scope = scope)
    }
}
