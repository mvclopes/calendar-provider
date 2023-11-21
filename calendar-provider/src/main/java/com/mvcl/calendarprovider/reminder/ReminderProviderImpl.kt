package com.mvcl.calendarprovider.reminder

import android.content.ContentResolver
import android.content.ContentValues
import android.provider.CalendarContract
import com.mvcl.calendarprovider.reminder.constants.ReminderConstants
import com.mvcl.calendarprovider.reminder.mapper.toReminderMethod
import com.mvcl.calendarprovider.reminder.model.ReminderDTO
import com.mvcl.calendarprovider.reminder.model.ReminderEntity

internal class ReminderProviderImpl(
    private val contentResolver: ContentResolver
) : ReminderProvider {
    override suspend fun getReminders(eventId: Long): List<ReminderEntity> {
        val reminders = mutableListOf<ReminderEntity>()

        val cursor = contentResolver.query(
            ReminderConstants.uri,
            ReminderConstants.projection,
            "(${CalendarContract.Reminders.EVENT_ID} = ?)",
            arrayOf(eventId.toString()),
            null
        )

        while (cursor?.moveToNext() == true) {
            cursor.apply {
                val id = getLong(ReminderConstants.PROJECTION_REMINDER_ID)
                val minutes = getInt(ReminderConstants.PROJECTION_REMINDER_MINUTES)
                val method = getInt(ReminderConstants.PROJECTION_REMINDER_METHOD)

                reminders.add(
                    ReminderEntity(
                        eventId = eventId,
                        id = id,
                        minutes = minutes,
                        method = method.toReminderMethod()
                    )
                )
            }
        }
        cursor?.close()

        return reminders
    }

    override suspend fun createReminder(eventId: Long, reminder: ReminderDTO) {
        val values = ContentValues().apply {
            put(CalendarContract.Reminders.EVENT_ID, eventId)
            put(CalendarContract.Reminders.MINUTES, reminder.minutes)
            put(CalendarContract.Reminders.METHOD, reminder.method.value)
        }
        contentResolver.insert(ReminderConstants.uri, values)
    }
}