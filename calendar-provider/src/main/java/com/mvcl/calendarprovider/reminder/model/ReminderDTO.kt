package com.mvcl.calendarprovider.reminder.model

data class ReminderDTO(
    val minutes: Int,
    val method: ReminderMethod,
)
