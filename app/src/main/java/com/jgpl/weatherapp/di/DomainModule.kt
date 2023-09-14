package com.jgpl.weatherapp.di

import com.jgpl.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.jgpl.weatherapp.domain.usecase.GetLocationsUseCase
import com.jgpl.weatherapp.domain.usecase.GetUserConfigUseCase
import com.jgpl.weatherapp.domain.usecase.SetUserConfigUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCurrentWeatherUseCase(get(), get()) }
    single { GetLocationsUseCase(get()) }
    single { GetUserConfigUseCase(get()) }
    single { SetUserConfigUseCase(get()) }
}
