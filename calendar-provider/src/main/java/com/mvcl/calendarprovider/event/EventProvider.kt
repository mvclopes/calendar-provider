package com.mvcl.calendarprovider.event

import com.mvcl.calendarprovider.calendar.model.CalendarDTO
import com.mvcl.calendarprovider.event.model.EventEntity

interface EventProvider {
    suspend fun getEvents(calendarId: Long): List<EventEntity>
    suspend fun deleteEvent(id: Long, calendarDTO: CalendarDTO)
}