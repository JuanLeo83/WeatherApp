package com.jgpl.weatherapp.ui.screen.settings.error

sealed interface SettingsError {
    object Unknown : SettingsError
}