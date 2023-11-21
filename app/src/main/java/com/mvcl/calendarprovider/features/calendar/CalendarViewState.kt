package com.mvcl.calendarprovider.features.calendar

import com.mvcl.calendarprovider.calendar.model.CalendarEntity

sealed class CalendarViewState {
    data class Error(val throwable: Throwable): CalendarViewState()
    object Idle: CalendarViewState()
    object Loading: CalendarViewState()
    data class Success(val calendars: List<CalendarEntity>): CalendarViewState()
}