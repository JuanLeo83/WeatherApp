package com.jgpl.weatherapp.di

import com.jgpl.weatherapp.ui.screen.current.CurrentViewModel
import com.jgpl.weatherapp.ui.screen.current.mapper.ConditionMapper
import com.jgpl.weatherapp.ui.screen.current.mapper.CurrentErrorMapper
import com.jgpl.weatherapp.ui.screen.current.mapper.CurrentVoMapper
import com.jgpl.weatherapp.ui.screen.settings.SettingsViewModel
import com.jgpl.weatherapp.ui.screen.settings.mapper.SettingsErrorMapper
import com.jgpl.weatherapp.ui.screen.settings.mapper.SettingsVoMapper
import com.jgpl.weatherapp.ui.screen.settings.mapper.SuggestionMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CurrentViewModel(get(), get(), get()) }
    single { ConditionMapper() }
    single { CurrentVoMapper(get(), get()) }
    single { CurrentErrorMapper() }

    viewModel { SettingsViewModel(get(), get(), get(), get(), get(), get()) }
    single { SettingsVoMapper() }
    single { SuggestionMapper() }
    single { SettingsErrorMapper() }
}