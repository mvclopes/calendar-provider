package com.mvcl.calendarprovider.reminder

import com.mvcl.calendarprovider.reminder.model.ReminderEntity

interface ReminderProvider {
    suspend fun getReminders(eventId: Long): List<ReminderEntity>
}