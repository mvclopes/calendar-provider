package com.mvcl.calendarprovider.features.event.model

import kotlinx.serialization.Serializable

@Serializable
data class EventArgs(
    val calendarId: Long,
    val accountName: String,
    val accountType: String,
)
