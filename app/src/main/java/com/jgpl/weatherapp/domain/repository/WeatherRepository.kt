package com.jgpl.weatherapp.domain.repository

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.utils.AppResult

interface WeatherRepository {
    suspend fun getCurrent(userConfig: UserConfigModel): AppResult<CurrentWeatherModel, AppError>
    suspend fun getCities(query: String): AppResult<List<CityModel>, AppError>
}