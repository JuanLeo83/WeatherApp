package com.jgpl.weatherapp.domain.repository

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.utils.AppResult

interface ConfigRepository {
    suspend fun getUserConfig(): AppResult<UserConfigModel, AppError>
    suspend fun setUserConfig(config: UserConfigModel): AppResult<Unit, AppError>
}