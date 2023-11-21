package com.mvcl.calendarprovider.reminder.model

data class ReminderEntity(
    val eventId: Long,
    val id: Long,
    val minutes: Int,
    val method: ReminderMethod,
)

enum class ReminderMethod { ALERT, DEFAULT, EMAIL, SMS }

fun String.toReminderMethod(): ReminderMethod {
    return runCatching {
        ReminderMethod.valueOf(this)
    }.getOrDefault(ReminderMethod.DEFAULT)
}
