package com.jgpl.weatherapp.domain.usecase

import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.ConfigRepository

class SetUserConfigUseCase(private val configRepository: ConfigRepository) {
    suspend operator fun invoke(input: UserConfigModel): Result<Unit> {
        return configRepository.setUserConfig(input)
    }
}