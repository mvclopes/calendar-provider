package com.mvcl.calendarprovider.features.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvcl.calendarprovider.event.EventProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventsViewModel(
    private val calendarId: Long,
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
                eventProvider.getEvents(calendarId)
            }.onSuccess {
                state.emit(EventViewState.Success(it))
            }.onFailure {
                state.emit(EventViewState.Error(it))
            }
        }
    }
}