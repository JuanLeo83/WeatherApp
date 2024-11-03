package com.jgpl.weatherapp.data.source.remote.weather

import com.jgpl.weatherapp.data.source.WeatherDataSource
import com.jgpl.weatherapp.data.source.remote.weather.api.WeatherApi
import com.jgpl.weatherapp.data.source.remote.weather.config.ApiKeyProvider
import com.jgpl.weatherapp.data.source.remote.weather.mapper.CityApiMapper
import com.jgpl.weatherapp.data.source.remote.weather.mapper.WeatherApiMapper
import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class RemoteWeatherDataSource(
    private val api: WeatherApi,
    private val apiKeyProvider: ApiKeyProvider,
    private val weatherMapper: WeatherApiMapper,
    private val cityMapper: CityApiMapper
) : WeatherDataSource {

    override suspend fun getCurrent(place: String): Result<CurrentWeatherModel> {
        return withContext(Dispatchers.IO) {
            apiKeyProvider.checkApiKey()
            val result = api.getToday(
                key = apiKeyProvider.getApiKey(),
                place = place,
                language = Locale.getDefault().language
            ).execute()
            if (result.isSuccessful) {
                result.body()?.let {
                    val today = weatherMapper.mapToModel(it)
                    Result.success(today)
                } ?: Result.failure(Exception())
            } else {
                // TODO: check error and map to correct error
                Result.failure(Exception())
            }
        }
    }

    override suspend fun getCities(query: String): Result<List<CityModel>> {
        return withContext(Dispatchers.IO) {
            apiKeyProvider.checkApiKey()
            val result = api.getCities(
                key = apiKeyProvider.getApiKey(),
                query = query
            ).execute()
            if (result.isSuccessful) {
                result.body()?.let {
                    val cities = cityMapper.map(it)
                    Result.success(cities)
                } ?: Result.failure(Exception())
            } else {
                // TODO: check error and map to correct error
                Result.failure(Exception())
            }
        }
    }

}