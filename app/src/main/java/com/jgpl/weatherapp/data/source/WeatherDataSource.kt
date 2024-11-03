package com.jgpl.weatherapp.data.source

import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel

interface WeatherDataSource {
    suspend fun getCurrent(place: String): Result<CurrentWeatherModel>
    suspend fun getCities(query: String): Result<List<CityModel>>
}