package com.jgpl.weatherapp.domain.usecase

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.CurrentWeatherAndUserConfig
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.repository.ConfigRepository
import com.jgpl.weatherapp.domain.repository.WeatherRepository
import com.jgpl.weatherapp.utils.AppResult
import com.jgpl.weatherapp.utils.onFailure
import com.jgpl.weatherapp.utils.onSuccess
import com.jgpl.weatherapp.utils.resultFailure
import com.jgpl.weatherapp.utils.resultSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetCurrentWeatherUseCase(
    private val configRepository: ConfigRepository,
    private val weatherRepository: WeatherRepository
) : UseCase<Unit, AppResult<CurrentWeatherAndUserConfig, AppError>>() {
    override fun prepareFlow(input: Unit): Flow<AppResult<CurrentWeatherAndUserConfig, AppError>> =
        flow {
            configRepository.getUserConfig()
                .onSuccess { config ->
                    weatherRepository.getCurrent(config)
                        .onSuccess { current ->
                            emit(
                                resultSuccess(
                                    CurrentWeatherAndUserConfig(
                                        config = config,
                                        current = current
                                    )
                                )
                            )
                        }
                        .onFailure {
                            emit(resultFailure(AppError.Unknown))
                        }
                }
                .onFailure {
                    emit(resultFailure(AppError.Unknown))
                }
        }.catch {
            emit(resultFailure(AppError.Unknown))
        }
}