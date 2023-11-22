package com.mvcl.calendarprovider.event

import android.content.ContentResolver
import android.content.ContentUris
import android.provider.CalendarContract
import android.util.Log
import com.mvcl.calendarprovider.calendar.model.CalendarDTO
import com.mvcl.calendarprovider.common.asSyncAdapter
import com.mvcl.calendarprovider.event.constants.EventConstants
import com.mvcl.calendarprovider.event.model.EventEntity

internal class EventProviderImpl(
    private val contentResolver: ContentResolver
) : EventProvider {
    override suspend fun getEvents(calendarId: Long): List<EventEntity> {
        val events = mutableListOf<EventEntity>()
        val cursor = contentResolver.query(
            EventConstants.uri,
            EventConstants.projection,
            "(${CalendarContract.Events.CALENDAR_ID} = ?)",
            arrayOf(calendarId.toString()),
            null
        )

        while (cursor?.moveToNext() == true) {
            cursor.apply {
                val id = getLong(EventConstants.PROJECTION_EVENT_ID)
                val title = getString(EventConstants.PROJECTION_EVENT_TITLE)
                val description = getString(EventConstants.PROJECTION_EVENT_DESCRIPTION)
                val location = getString(EventConstants.PROJECTION_EVENT_LOCATION)
                val dateStart = getLong(EventConstants.PROJECTION_EVENT_DATE_START)
                val dateEnd = getLong(EventConstants.PROJECTION_EVENT_DATE_END)
                val recurrenceRule = getString(EventConstants.PROJECTION_EVENT_RECURRENCE_RULE)
                val duration = getString(EventConstants.PROJECTION_EVENT_DURATION)
                val isAllDay = getInt(EventConstants.PROJECTION_EVENT_IS_ALL_DAY)
                val displayColor = getInt(EventConstants.PROJECTION_EVENT_DISPLAY_COLOR)
                val ownerAccount = getString(EventConstants.PROJECTION_EVENT_OWNER_ACCOUNT)
                events.add(
                    EventEntity(
                        calendarId = calendarId,
                        id = id,
                        title = title,
                        description = description,
                        location = location,
                        dateStart = dateStart,
                        dateEnd = dateEnd,
                        recurrenceRule = recurrenceRule,
                        duration = duration,
                        isAllDay = isAllDay == 1,
                        displayColor = displayColor,
                        ownerAccount = ownerAccount
                    )
                )
            }
        }
        cursor?.close()
        Log.i("TAG_MVCL", "getEvents result size: ${events.size}")

        return events
    }

    override suspend fun deleteEvent(id: Long, calendarDTO: CalendarDTO) {
        val uri = EventConstants.uri.asSyncAdapter(
            accountName = calendarDTO.accountName,
            accountType = calendarDTO.accountType
        )
        val deleteUri = ContentUris.withAppendedId(uri, id)
        contentResolver.delete(deleteUri, null, null)
    }
}