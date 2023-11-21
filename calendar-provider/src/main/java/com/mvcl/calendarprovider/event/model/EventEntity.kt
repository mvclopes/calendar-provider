package com.mvcl.calendarprovider.event.model

data class EventEntity(
    val calendarId: Long,
    val id: Long,
    val title: String,
    val description: String?,
    val location: String,
    val dateStart: Long,
    val dateEnd: Long,
    val recurrenceRule: String?,
    val duration: String?,
    val isAllDay: Boolean,
    val displayColor: Int,
    val ownerAccount: String?
)
