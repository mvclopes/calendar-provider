package com.mvcl.calendarprovider.reminder.constants

import android.net.Uri
import android.provider.CalendarContract
import com.mvcl.calendarprovider.common.CommonConstants

internal object ReminderConstants: CommonConstants {
    override val uri: Uri = CalendarContract.Reminders.CONTENT_URI
    override val projection = arrayOf(
        CalendarContract.Reminders._ID,
        CalendarContract.Reminders.MINUTES,
        CalendarContract.Reminders.METHOD
    )
    const val PROJECTION_REMINDER_ID = 0
    const val PROJECTION_REMINDER_MINUTES = 1
    const val PROJECTION_REMINDER_METHOD = 2
}
