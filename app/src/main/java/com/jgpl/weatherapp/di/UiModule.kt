package com.jgpl.weatherapp.di

import com.jgpl.weatherapp.ui.screen.current.CurrentViewModel
import com.jgpl.weatherapp.ui.screen.current.mapper.ConditionMapper
import com.jgpl.weatherapp.ui.screen.current.mapper.CurrentVoMapper
import com.jgpl.weatherapp.ui.screen.settings.SettingsViewModel
import com.jgpl.weatherapp.ui.screen.settings.mapper.SettingsVoMapper
import com.jgpl.weatherapp.ui.screen.settings.mapper.SuggestionMapper
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::CurrentViewModel)
    single { ConditionMapper() }
    single { CurrentVoMapper(get(), get()) }

    viewModelOf(::SettingsViewModel)
    single { SettingsVoMapper() }
    single { SuggestionMapper() }
}