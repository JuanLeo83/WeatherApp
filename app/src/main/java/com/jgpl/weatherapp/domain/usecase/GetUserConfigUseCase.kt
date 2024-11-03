package com.jgpl.weatherapp.domain.usecase

import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.ConfigRepository

class GetUserConfigUseCase(private val configRepository: ConfigRepository) {
    suspend operator fun invoke(): Result<UserConfigModel> {
        return configRepository.getUserConfig()
    }
}