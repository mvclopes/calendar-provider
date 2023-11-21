package com.mvcl.calendarprovider.reminder

import android.content.ContentResolver
import android.provider.CalendarContract
import android.util.Log
import com.mvcl.calendarprovider.reminder.constants.ReminderConstants
import com.mvcl.calendarprovider.reminder.model.ReminderEntity
import com.mvcl.calendarprovider.reminder.model.toReminderMethod

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
                val method = getString(ReminderConstants.PROJECTION_REMINDER_METHOD)

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
        Log.i("TAG_MVCL", "getReminders result size: ${reminders.size}")

        return reminders
    }
}