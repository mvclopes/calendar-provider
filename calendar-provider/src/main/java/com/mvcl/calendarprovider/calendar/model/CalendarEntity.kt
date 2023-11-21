package com.mvcl.calendarprovider.calendar.model

data class CalendarEntity(
    val id: Long,
    val displayName: String,
    val color: Int,
    val ownerAccount: String,
    val maxRemindersAllowed: Int
)
