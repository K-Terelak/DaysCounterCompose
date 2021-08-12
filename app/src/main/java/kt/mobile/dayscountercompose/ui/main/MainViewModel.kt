package kt.mobile.dayscountercompose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kt.mobile.dayscountercompose.data.DatesRepository
import kt.mobile.dayscountercompose.data.entity.DateEntity
import kt.mobile.dayscountercompose.util.ViewState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DatesRepository
) : ViewModel() {

    init {
        getAllDates()
    }

    private val _datesList = MutableStateFlow<ViewState>(ViewState.Loading)
    val datesList: StateFlow<ViewState> = _datesList

    private fun getAllDates() {
        viewModelScope.launch {
            try {
                repository.local.getAllDates().collect {
                    if (it.isEmpty()) {
                        _datesList.value = ViewState.Empty
                    } else {
                        _datesList.value = ViewState.Success(it)
                    }
                }
            } catch (e: Exception) {
                _datesList.value = ViewState.Error(e)
            }
        }
    }

    fun insertDate(dateEntity: DateEntity) {
        viewModelScope.launch(IO) {
            repository.local.insertDate(dateEntity)
        }
    }

    fun deleteDate(dateEntity: DateEntity) {
        viewModelScope.launch(IO) {
            repository.local.deleteDate(dateEntity)
        }
    }
}