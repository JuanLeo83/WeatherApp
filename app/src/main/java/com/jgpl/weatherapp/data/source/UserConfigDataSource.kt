package com.jgpl.weatherapp.data.source

import com.jgpl.weatherapp.domain.model.UserConfigModel

interface UserConfigDataSource {
    suspend fun getUserConfig(): Result<UserConfigModel>
    suspend fun setUserConfig(userConfig: UserConfigModel): Result<Unit>
}