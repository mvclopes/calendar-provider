package com.mvcl.calendarprovider.calendar.constants

import android.net.Uri
import android.provider.CalendarContract
import com.mvcl.calendarprovider.common.CommonConstants

internal object CalendarConstants: CommonConstants {
    override val uri: Uri = CalendarContract.Calendars.CONTENT_URI
    override val projection = arrayOf(
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