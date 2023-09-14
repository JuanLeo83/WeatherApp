package com.jgpl.weatherapp

import android.app.Application
import com.jgpl.weatherapp.di.dataModule
import com.jgpl.weatherapp.di.domainModule
import com.jgpl.weatherapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(
                uiModule,
                domainModule,
                dataModule
            )
        }
    }

}