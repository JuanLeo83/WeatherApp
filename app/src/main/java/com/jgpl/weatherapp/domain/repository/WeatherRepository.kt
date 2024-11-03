package com.jgpl.weatherapp.domain.repository

import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.model.UserConfigModel

interface WeatherRepository {
    suspend fun getCurrent(userConfig: UserConfigModel): Result<CurrentWeatherModel>
    suspend fun getCities(query: String): Result<List<CityModel>>
}