package com.muhtarkhan.ncourseproject

import android.app.Application
import com.muhtarkhan.ncourseproject.di.appModule
import com.muhtarkhan.ncourseproject.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class
MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(networkModule, appModule))
        }
    }
}