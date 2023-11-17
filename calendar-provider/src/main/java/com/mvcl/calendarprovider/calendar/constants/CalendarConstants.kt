package com.mvcl.calendarprovider.calendar.constants

import android.provider.CalendarContract

internal object CalendarConstants {
    val calendarUri = CalendarContract.Calendars.CONTENT_URI
    val PROJECTION = arrayOf(
        CalendarContract.Calendars._ID,
        CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
        CalendarContract.Calendars.CALENDAR_COLOR,
        CalendarContract.Calendars.OWNER_ACCOUNT
    )
    const val PROJECTION_ID = 0
    const val PROJECTION_DISPLAY_NAME = 1
    const val PROJECTION_CALENDAR_COLOR = 2
    const val PROJECTION_OWNER_ACCOUNT = 3
}