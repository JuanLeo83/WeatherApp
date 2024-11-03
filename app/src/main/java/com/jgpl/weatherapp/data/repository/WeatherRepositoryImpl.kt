package com.jgpl.weatherapp.data.repository

import com.jgpl.weatherapp.data.source.WeatherDataSource
import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun getCurrent(userConfig: UserConfigModel): Result<CurrentWeatherModel> {
        return weatherDataSource.getCurrent(userConfig.city)
    }

    override suspend fun getCities(query: String): Result<List<CityModel>> {
        return weatherDataSource.getCities(query)
    }
}