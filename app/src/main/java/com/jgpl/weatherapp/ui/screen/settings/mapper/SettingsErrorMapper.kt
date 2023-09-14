package com.jgpl.weatherapp.ui.screen.settings.mapper

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.ui.screen.settings.error.SettingsError

class SettingsErrorMapper {

    fun map(error: AppError): SettingsError {
        return when (error) {
            AppError.Unknown -> SettingsError.Unknown
        }
    }
}