package com.jgpl.weatherapp.ui.screen.current.vo

data class TodayHourForecastItemVo(
    val hour: String,
    val temperature: String,
    val icon: Int,
    val conditionText: String,
    val isCurrentHour: Boolean = false
)
