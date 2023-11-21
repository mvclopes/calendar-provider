package com.mvcl.calendarprovider.reminder.model

enum class ReminderMethod(val value: Int) {
    ALARM(value = 4),
    ALERT(value = 1),
    DEFAULT(value = 0),
    EMAIL(value = 2),
    SMS(value = 3)
}
