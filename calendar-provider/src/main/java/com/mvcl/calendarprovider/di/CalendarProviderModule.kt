package com.mvcl.calendarprovider.di

import com.mvcl.calendarprovider.calendar.CalendarProvider
import com.mvcl.calendarprovider.calendar.CalendarProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object CalendarProviderModule {
    val module = module {
        factory<CalendarProvider> {
            CalendarProviderImpl(
                contentResolver = androidContext().contentResolver
            )
        }
    }
}