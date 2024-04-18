package seek.codingtask.searchform.presentation

import com.seek.android.core.presentation.mvi.UiEvent

sealed class SearchFormUiEvent : UiEvent {
    data class KeywordsChanged(val newValue: String) : SearchFormUiEvent()

    data object SubmitPressed : SearchFormUiEvent()
}
