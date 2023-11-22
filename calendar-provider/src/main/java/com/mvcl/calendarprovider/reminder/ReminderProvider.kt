package com.mvcl.calendarprovider.reminder

import com.mvcl.calendarprovider.reminder.model.ReminderDTO
import com.mvcl.calendarprovider.reminder.model.ReminderEntity

interface ReminderProvider {
    suspend fun getReminders(eventId: Long): List<ReminderEntity>
    suspend fun createReminder(eventId: Long, reminder: ReminderDTO)
    suspend fun deleteReminder(id: Long)
}