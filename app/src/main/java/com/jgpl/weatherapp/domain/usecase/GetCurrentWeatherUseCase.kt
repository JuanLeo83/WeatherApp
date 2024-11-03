package com.jgpl.weatherapp.domain.usecase

import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(config: UserConfigModel): Result<CurrentWeatherModel> {
        return weatherRepository.getCurrent(config)
    }
}