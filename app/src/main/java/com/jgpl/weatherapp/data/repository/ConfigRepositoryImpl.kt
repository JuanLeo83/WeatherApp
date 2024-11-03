package com.jgpl.weatherapp.data.repository

import com.jgpl.weatherapp.data.source.UserConfigDataSource
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.ConfigRepository

class ConfigRepositoryImpl(
    private val userConfigDataSource: UserConfigDataSource
) : ConfigRepository {
    override suspend fun getUserConfig(): Result<UserConfigModel> {
        return userConfigDataSource.getUserConfig()
    }

    override suspend fun setUserConfig(config: UserConfigModel): Result<Unit> {
        return userConfigDataSource.setUserConfig(config)
    }
}