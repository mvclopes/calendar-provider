package com.mvcl.calendarprovider.di

import com.mvcl.calendarprovider.features.calendar.CalendarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val module = module {
        viewModel {
            CalendarsViewModel(
                calendarProvider = get()
            )
        }
    }
}