package seek.codingtask.jobdetails.presentation

import com.seek.android.core.common.errors.ErrorReason
import com.seek.android.core.presentation.mvi.UiState
import kotlinx.parcelize.Parcelize
import seek.codingtask.jobdetails.domain.model.JobDetailsItem

@Parcelize
sealed class JobDetailsUIState : UiState {
    object Loading : JobDetailsUIState()
    data class Success(val data: JobDetailsItem) : JobDetailsUIState()
    data class Error(val reason: ErrorReason) : JobDetailsUIState()
}
