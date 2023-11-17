package com.mvcl.calendarprovider

import android.app.Application
import com.mvcl.calendarprovider.di.AppModule
import com.mvcl.calendarprovider.di.CalendarProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                CalendarProviderModule.module,
                AppModule.module
            )
        }
    }
}