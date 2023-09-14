package com.jgpl.weatherapp.data.repository

import com.jgpl.weatherapp.data.source.WeatherDataSource
import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.WeatherRepository
import com.jgpl.weatherapp.utils.AppResult

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun getCurrent(userConfig: UserConfigModel): AppResult<CurrentWeatherModel, AppError> {
        return weatherDataSource.getCurrent(userConfig.city)
    }

    override suspend fun getCities(query: String): AppResult<List<CityModel>, AppError> {
        return weatherDataSource.getCities(query)
    }
}