package com.mvcl.calendarprovider.di

import com.mvcl.calendarprovider.calendar.CalendarProvider
import com.mvcl.calendarprovider.calendar.CalendarProviderImpl
import com.mvcl.calendarprovider.event.EventProvider
import com.mvcl.calendarprovider.event.EventProviderImpl
import com.mvcl.calendarprovider.reminder.ReminderProvider
import com.mvcl.calendarprovider.reminder.ReminderProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
import org.koin.dsl.module

object CalendarProviderModule {
    private fun Scope.getContentResolver() = androidContext().contentResolver

    val module = module {
        factory<CalendarProvider> {
            CalendarProviderImpl(contentResolver = getContentResolver())
        }

        factory<EventProvider> {
            EventProviderImpl(contentResolver = getContentResolver())
        }

        factory<ReminderProvider> {
            ReminderProviderImpl(contentResolver = getContentResolver())
        }
    }
}