package com.mvcl.calendarprovider.calendar.model

interface CalendarProvider {
    fun getCalendars(): List<CalendarEntity>
}