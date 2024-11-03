package com.jgpl.weatherapp.domain.usecase

import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.domain.repository.WeatherRepository

class GetLocationsUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(input: String): Result<List<CityModel>> {
        return weatherRepository.getCities(input)
    }
}