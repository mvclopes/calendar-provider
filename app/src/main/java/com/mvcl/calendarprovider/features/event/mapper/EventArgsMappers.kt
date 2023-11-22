package com.mvcl.calendarprovider.features.event.mapper

import com.mvcl.calendarprovider.calendar.model.CalendarDTO
import com.mvcl.calendarprovider.calendar.model.CalendarEntity
import com.mvcl.calendarprovider.features.event.model.EventArgs

fun EventArgs.toCalendarDTO() = CalendarDTO(
    id = calendarId,
    accountName = accountName,
    accountType = accountType
)

fun CalendarEntity.toEventArgs() = EventArgs(
    calendarId = id,
    accountName = accountName,
    accountType = accountType
)