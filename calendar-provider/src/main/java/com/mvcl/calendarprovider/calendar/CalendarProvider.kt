package com.mvcl.calendarprovider.calendar

import com.mvcl.calendarprovider.calendar.model.CalendarEntity

interface CalendarProvider {
    fun getCalendars(): List<CalendarEntity>
}