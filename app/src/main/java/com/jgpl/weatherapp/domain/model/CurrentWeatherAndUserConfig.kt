package com.jgpl.weatherapp.domain.model

data class CurrentWeatherAndUserConfig(
    val config: UserConfigModel,
    val current: CurrentWeatherModel
)
