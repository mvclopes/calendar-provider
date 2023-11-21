package com.mvcl.calendarprovider.features.event

import com.mvcl.calendarprovider.event.model.EventEntity

sealed class EventViewState {
    data class Error(val throwable: Throwable): EventViewState()
    object Idle: EventViewState()
    object Loading: EventViewState()
    data class Success(val events: List<EventEntity>): EventViewState()
}
