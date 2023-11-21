package com.mvcl.calendarprovider.event

import com.mvcl.calendarprovider.event.model.EventEntity

interface EventProvider {
    fun getEvents(calendarId: Long): List<EventEntity>
}