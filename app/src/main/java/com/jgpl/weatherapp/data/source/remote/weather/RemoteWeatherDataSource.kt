package com.jgpl.weatherapp.data.source.remote.weather

import com.jgpl.weatherapp.data.source.WeatherDataSource
import com.jgpl.weatherapp.data.source.remote.weather.api.WeatherApi
import com.jgpl.weatherapp.data.source.remote.weather.config.ApiKeyProvider
import com.jgpl.weatherapp.data.source.remote.weather.mapper.CityApiMapper
import com.jgpl.weatherapp.data.source.remote.weather.mapper.WeatherApiMapper
import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.utils.AppResult
import com.jgpl.weatherapp.utils.resultFailure
import com.jgpl.weatherapp.utils.resultSuccess
import java.util.Locale

class RemoteWeatherDataSource(
    private val api: WeatherApi,
    private val apiKeyProvider: ApiKeyProvider,
    private val weatherMapper: WeatherApiMapper,
    private val cityMapper: CityApiMapper
) : WeatherDataSource {

    override suspend fun getCurrent(place: String): AppResult<CurrentWeatherModel, AppError> {
        apiKeyProvider.checkApiKey()
        val result = api.getToday(
            key = apiKeyProvider.getApiKey(),
            place = place,
            language = Locale.getDefault().language
        ).execute()
        return if (result.isSuccessful) {
            result.body()?.let {
                val today = weatherMapper.mapToModel(it)
                resultSuccess(today)
            } ?: resultFailure(AppError.Unknown)
        } else {
            // TODO: check error and map to correct error
            resultFailure(AppError.Unknown)
        }
    }

    override suspend fun getCities(query: String): AppResult<List<CityModel>, AppError> {
        apiKeyProvider.checkApiKey()
        val result = api.getCities(
            key = apiKeyProvider.getApiKey(),
            query = query
        ).execute()
        return if (result.isSuccessful) {
            result.body()?.let {
                val cities = cityMapper.map(it)
                resultSuccess(cities)
            } ?: resultFailure(AppError.Unknown)
        } else {
            // TODO: check error and map to correct error
            resultFailure(AppError.Unknown)
        }
    }

}