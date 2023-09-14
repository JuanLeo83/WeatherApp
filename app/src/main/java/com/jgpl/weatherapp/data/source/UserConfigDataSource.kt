package com.jgpl.weatherapp.data.source

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.utils.AppResult

interface UserConfigDataSource {
    suspend fun getUserConfig(): AppResult<UserConfigModel, AppError>
    suspend fun setUserConfig(userConfig: UserConfigModel): AppResult<Unit, AppError>
}