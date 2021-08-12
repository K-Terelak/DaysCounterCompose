package kt.mobile.dayscountercompose.util

import kt.mobile.dayscountercompose.data.entity.DateEntity


sealed class ViewState {
    object Empty: ViewState()
    object Loading : ViewState()
    data class Success(val data: List<DateEntity>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}
