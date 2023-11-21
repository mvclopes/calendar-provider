package com.mvcl.calendarprovider.reminder.mapper

import com.mvcl.calendarprovider.reminder.model.ReminderMethod

fun String.toReminderMethod(): ReminderMethod {
    return runCatching {
        ReminderMethod.valueOf(this)
    }.getOrDefault(ReminderMethod.DEFAULT)
}

fun Int.toReminderMethod(): ReminderMethod {
    return runCatching {
        ReminderMethod.values().first { it.value == this }
    }.getOrDefault(ReminderMethod.DEFAULT)
}