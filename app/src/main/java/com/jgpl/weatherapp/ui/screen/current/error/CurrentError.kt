package com.jgpl.weatherapp.ui.screen.current.error

sealed interface CurrentError {
    object Unknown : CurrentError
}