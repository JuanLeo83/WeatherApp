package com.jgpl.weatherapp.domain.model

data class NextDayModel(
    val date: String,
    val minTemperature: TemperatureModel,
    val maxTemperature: TemperatureModel,
    val condition: ConditionModel
)
