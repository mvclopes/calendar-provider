package com.mvcl.calendarprovider.features.reminder

import com.mvcl.calendarprovider.reminder.model.ReminderEntity

sealed class ReminderViewState {
    data class Error(val throwable: Throwable): ReminderViewState()
    object Idle: ReminderViewState()
    object Loading: ReminderViewState()
    data class Success(val reminders: List<ReminderEntity>): ReminderViewState()
}
