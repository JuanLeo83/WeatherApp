package com.jgpl.weatherapp.domain.error

sealed interface AppError {
    object Unknown : AppError
}