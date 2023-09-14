package com.jgpl.weatherapp.data.repository

import com.jgpl.weatherapp.data.source.UserConfigDataSource
import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.ConfigRepository
import com.jgpl.weatherapp.utils.AppResult

class ConfigRepositoryImpl(
    private val userConfigDataSource: UserConfigDataSource
) : ConfigRepository {
    override suspend fun getUserConfig(): AppResult<UserConfigModel, AppError> {
        return userConfigDataSource.getUserConfig()
    }

    override suspend fun setUserConfig(config: UserConfigModel): AppResult<Unit, AppError> {
        return userConfigDataSource.setUserConfig(config)
    }
}