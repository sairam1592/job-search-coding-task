package seek.codingtask.searchform.presentation

import androidx.annotation.StringRes
import com.seek.android.core.presentation.mvi.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class SearchFormUiState : UiState {
    data class Form(
        val keywords: String,
        @StringRes val keywordsError: Int? = null,
    ) : SearchFormUiState()
}
