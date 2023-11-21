package com.mvcl.calendarprovider.reminder.constants

import android.net.Uri
import android.provider.CalendarContract

internal object ReminderConstants {
    val reminderUri: Uri = CalendarContract.Reminders.CONTENT_URI
    val PROJECTION = arrayOf(
        CalendarContract.Reminders._ID,
        CalendarContract.Reminders.MINUTES,
        CalendarContract.Reminders.METHOD
    )
    const val PROJECTION_REMINDER_ID = 0
    const val PROJECTION_REMINDER_MINUTES = 1
    const val PROJECTION_REMINDER_METHOD = 2
}