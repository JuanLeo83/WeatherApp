package com.jgpl.weatherapp.domain.repository

import com.jgpl.weatherapp.domain.model.UserConfigModel

interface ConfigRepository {
    suspend fun getUserConfig(): Result<UserConfigModel>
    suspend fun setUserConfig(config: UserConfigModel): Result<Unit>
}