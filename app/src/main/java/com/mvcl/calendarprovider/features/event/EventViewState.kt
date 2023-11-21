package com.mvcl.calendarprovider.features.event

import com.mvcl.calendarprovider.event.model.EventEntity

sealed class EventViewState {
    object Error: EventViewState()
    object Idle: EventViewState()
    object Loading: EventViewState()
    data class Success(val events: List<EventEntity>): EventViewState()
}
