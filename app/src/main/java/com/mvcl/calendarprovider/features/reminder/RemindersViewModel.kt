package com.mvcl.calendarprovider.features.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvcl.calendarprovider.reminder.ReminderProvider
import com.mvcl.calendarprovider.reminder.model.ReminderDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RemindersViewModel(
    private val eventId: Long,
    private val reminderProvider: ReminderProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    var state = MutableStateFlow<ReminderViewState>(ReminderViewState.Idle)
        private set

    init {
        getReminders()
    }

    private fun getReminders() {
        viewModelScope.launch(dispatcher) {
            state.emit(ReminderViewState.Loading)
            delay(1500)
            runCatching {
                reminderProvider.getReminders(eventId)
            }.onSuccess {
                state.emit(ReminderViewState.Success(it))
            }.onFailure {
                state.emit(ReminderViewState.Error(it))
            }
        }
    }

    fun createReminder(reminder: ReminderDTO) {
        viewModelScope.launch(dispatcher) {
            runCatching {
                reminderProvider.createReminder(eventId, reminder)
            }.onSuccess {
                getReminders()
            }.onFailure {
                state.emit(ReminderViewState.Error(it))
            }
        }
    }

    fun deleteReminder(id: Long) {
        viewModelScope.launch(dispatcher) {
            runCatching {
                reminderProvider.deleteReminder(id)
            }.onSuccess {
                getReminders()
            }.onFailure {
                state.emit(ReminderViewState.Error(it))
            }
        }
    }
}
