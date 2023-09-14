package com.jgpl.weatherapp.domain.usecase

import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.repository.ConfigRepository
import com.jgpl.weatherapp.utils.AppResult
import com.jgpl.weatherapp.utils.resultFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SetUserConfigUseCase(
    private val configRepository: ConfigRepository
) : UseCase<UserConfigModel, AppResult<Unit, AppError>>() {
    override fun prepareFlow(input: UserConfigModel): Flow<AppResult<Unit, AppError>> = flow {
        emit(configRepository.setUserConfig(input))
    }.catch { emit(resultFailure(AppError.Unknown)) }
}