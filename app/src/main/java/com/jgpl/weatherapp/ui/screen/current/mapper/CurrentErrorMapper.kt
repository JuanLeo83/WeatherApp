package com.jgpl.weatherapp.ui.screen.current.mapper

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.ui.screen.current.error.CurrentError

class CurrentErrorMapper {

    fun map(error: AppError): CurrentError {
        return when (error) {
            AppError.Unknown -> CurrentError.Unknown
        }
    }

}