package seek.codingtask.jobdetails.presentation

import com.seek.android.core.presentation.mvi.UiEvent

sealed class JobDetailsUIEvent : UiEvent {
    object Retry : JobDetailsUIEvent()
}
