package com.jgpl.weatherapp.domain.usecase

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.repository.WeatherRepository
import com.jgpl.weatherapp.utils.AppResult
import com.jgpl.weatherapp.utils.resultFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetLocationsUseCase(
    private val weatherRepository: WeatherRepository
) : UseCase<String, AppResult<List<CityModel>, AppError>>() {
    override fun prepareFlow(input: String): Flow<AppResult<List<CityModel>, AppError>> = flow {
        emit(weatherRepository.getCities(input))
    }.catch { emit(resultFailure(AppError.Unknown)) }
}