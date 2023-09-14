package com.jgpl.weatherapp.domain.model

data class HourForecastModel(
    val time: String,
    val temperature: TemperatureModel,
    val condition: ConditionModel,
    val isDay: Boolean
)
