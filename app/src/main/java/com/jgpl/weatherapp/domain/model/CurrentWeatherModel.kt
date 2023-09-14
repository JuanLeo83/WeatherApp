package com.jgpl.weatherapp.domain.model

data class CurrentWeatherModel(
    val name: String,
    val temperature: TemperatureModel,
    val pressure: PressureModel,
    val condition: ConditionModel,
    val precipitation: PrecipitationModel,
    val humidity: Int,
    val wind: WindModel,
    val todayHourForecast: List<HourForecastModel>,
    val nextDaysForecast: List<NextDayModel>
)
