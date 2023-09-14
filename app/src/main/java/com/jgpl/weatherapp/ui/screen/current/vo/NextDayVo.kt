package com.jgpl.weatherapp.ui.screen.current.vo

data class NextDayVo(
    val date: String,
    val maxTemperature: String,
    val minTemperature: String,
    val icon: Int,
    val conditionText: String
)
