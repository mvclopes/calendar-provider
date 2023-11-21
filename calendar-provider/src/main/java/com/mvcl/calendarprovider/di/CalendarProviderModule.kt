package com.mvcl.calendarprovider.di

import com.mvcl.calendarprovider.calendar.CalendarProvider
import com.mvcl.calendarprovider.calendar.CalendarProviderImpl
import com.mvcl.calendarprovider.event.EventProvider
import com.mvcl.calendarprovider.event.EventProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object CalendarProviderModule {
    val module = module {
        factory<CalendarProvider> {
            CalendarProviderImpl(
                contentResolver = androidContext().contentResolver
            )
        }

        factory<EventProvider> {
            EventProviderImpl(
                contentResolver = androidContext().contentResolver
            )
        }
    }
}