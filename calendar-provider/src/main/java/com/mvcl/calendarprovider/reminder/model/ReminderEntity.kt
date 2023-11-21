package com.mvcl.calendarprovider.reminder.model

data class ReminderEntity(
    val eventId: Long,
    val id: Long,
    val minutes: Int,
    val method: ReminderMethod,
)
