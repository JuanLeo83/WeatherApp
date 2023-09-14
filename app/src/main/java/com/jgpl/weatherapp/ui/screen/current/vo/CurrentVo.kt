package com.jgpl.weatherapp.ui.screen.current.vo

data class CurrentVo(
    val cityName: String = "Madrid",
    val date: String = "",
    val temperature: String = "",
    val conditionIcon: String = "",
    val conditionText: String = "",
    val humidity: ExtraInfoVo = ExtraInfoVo(),
    val precipitation: ExtraInfoVo = ExtraInfoVo(),
    val windSpeed: ExtraInfoVo = ExtraInfoVo(),
    val windDirection: ExtraInfoVo = ExtraInfoVo(),
    val todayHourForecast: List<TodayHourForecastItemVo> = listOf(),
    val nextDaysForecast: List<NextDayVo> = listOf()
)
