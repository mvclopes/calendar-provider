package com.mvcl.calendarprovider.calendar

import android.content.ContentResolver
import android.util.Log
import com.mvcl.calendarprovider.calendar.constants.CalendarConstants
import com.mvcl.calendarprovider.calendar.model.CalendarEntity

internal class CalendarProviderImpl(
    private val contentResolver: ContentResolver
) : CalendarProvider {

    override fun getCalendars(): List<CalendarEntity> {
        val calendars = mutableListOf<CalendarEntity>()
         val cursor = contentResolver.query(
             CalendarConstants.calendarUri,
             CalendarConstants.PROJECTION,
             "",
             emptyArray<String>(),
             null
         )

        while (cursor?.moveToNext() == true) {
            cursor.apply {
                val id = getLong(CalendarConstants.PROJECTION_ID)
                val displayName = getString(CalendarConstants.PROJECTION_DISPLAY_NAME)
                val color = getInt(CalendarConstants.PROJECTION_CALENDAR_COLOR)
                val ownerAccount = getString(CalendarConstants.PROJECTION_OWNER_ACCOUNT)
                calendars.add(
                    CalendarEntity(
                        id = id,
                        displayName = displayName,
                        color = color,
                        ownerAccount = ownerAccount
                    )
                )
            }
        }
        cursor?.close()
        Log.i("TAG_MVCL", "getCalendars result size:  ${calendars.size}")

        return calendars
    }
}