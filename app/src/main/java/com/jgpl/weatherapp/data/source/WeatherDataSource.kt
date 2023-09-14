package com.jgpl.weatherapp.data.source

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.utils.AppResult

interface WeatherDataSource {
    fun getCurrent(place: String): AppResult<CurrentWeatherModel, AppError>
    fun getCities(query: String): AppResult<List<CityModel>, AppError>
}