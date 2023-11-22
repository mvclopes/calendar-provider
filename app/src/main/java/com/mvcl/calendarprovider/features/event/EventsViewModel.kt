package com.mvcl.calendarprovider.features.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvcl.calendarprovider.event.EventProvider
import com.mvcl.calendarprovider.features.event.mapper.toCalendarDTO
import com.mvcl.calendarprovider.features.event.model.EventArgs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventsViewModel(
    private val args: EventArgs,
    private val eventProvider: EventProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    var state = MutableStateFlow<EventViewState>(EventViewState.Idle)
        private set

    init {
        getEvents()
    }

    private fun getEvents() {
        viewModelScope.launch(dispatcher) {
            state.emit(EventViewState.Loading)
            delay(1500)
            runCatching {
                eventProvider.getEvents(args.calendarId)
            }.onSuccess {
                state.emit(EventViewState.Success(it))
            }.onFailure {
                state.emit(EventViewState.Error(it))
            }
        }
    }

    fun deleteEvent(id: Long) {
        viewModelScope.launch(dispatcher) {
            runCatching {
                eventProvider.deleteEvent(id, args.toCalendarDTO())
            }.onSuccess {
                getEvents()
            }.onFailure {
                state.emit(EventViewState.Error(it))
            }
        }
    }
}