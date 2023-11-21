package com.mvcl.calendarprovider.features.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvcl.calendarprovider.calendar.CalendarProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CalendarsViewModel(
    private val calendarProvider: CalendarProvider
): ViewModel() {

    var state = MutableStateFlow<CalendarViewState>(CalendarViewState.Idle)
        private set

    init {
        getCalendars()
    }

    private fun getCalendars() {
        viewModelScope.launch {
            state.emit(CalendarViewState.Loading)
            delay(1500)
            runCatching {
                calendarProvider.getCalendars()
            }.onSuccess {
                state.emit(CalendarViewState.Success(it))
            }.onFailure {
                state.emit(CalendarViewState.Error)
            }
        }
    }
}
