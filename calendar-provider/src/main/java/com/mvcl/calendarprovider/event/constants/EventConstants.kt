package com.mvcl.calendarprovider.event.constants

import android.net.Uri
import android.provider.CalendarContract

internal object EventConstants {
    val eventUri: Uri = CalendarContract.Events.CONTENT_URI
    val PROJECTION = arrayOf(
        CalendarContract.Calendars._ID,
        CalendarContract.Events._ID,
        CalendarContract.Events.TITLE,
        CalendarContract.Events.DESCRIPTION,
        CalendarContract.Events.EVENT_LOCATION,
        CalendarContract.Events.DTSTART,
        CalendarContract.Events.DTEND,
        CalendarContract.Events.RRULE,
        CalendarContract.Events.DURATION,
        CalendarContract.Events.ALL_DAY,
        CalendarContract.Events.DISPLAY_COLOR,
        CalendarContract.Events.OWNER_ACCOUNT
    )
    const val PROJECTION_EVENT_ID = 1
    const val PROJECTION_EVENT_TITLE = 2
    const val PROJECTION_EVENT_DESCRIPTION = 3
    const val PROJECTION_EVENT_LOCATION = 4
    const val PROJECTION_EVENT_DATE_START = 5
    const val PROJECTION_EVENT_DATE_END = 6
    const val PROJECTION_EVENT_RECURRENCE_RULE = 7
    const val PROJECTION_EVENT_DURATION = 8
    const val PROJECTION_EVENT_IS_ALL_DAY = 9
    const val PROJECTION_EVENT_DISPLAY_COLOR = 10
    const val PROJECTION_EVENT_OWNER_ACCOUNT = 11

}