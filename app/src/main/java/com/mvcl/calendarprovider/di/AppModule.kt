package com.mvcl.calendarprovider.di

import com.mvcl.calendarprovider.features.calendar.CalendarsViewModel
import com.mvcl.calendarprovider.features.event.EventsViewModel
import com.mvcl.calendarprovider.features.reminder.RemindersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val module = module {
        viewModel {
            CalendarsViewModel(
                calendarProvider = get()
            )
        }

        viewModel { (calendarId: Long) ->
            EventsViewModel(
                calendarId = calendarId,
                eventProvider = get()
            )
        }

        viewModel { (eventId: Long) ->
            RemindersViewModel(
                eventId = eventId,
                reminderProvider = get()
            )
        }
    }
}