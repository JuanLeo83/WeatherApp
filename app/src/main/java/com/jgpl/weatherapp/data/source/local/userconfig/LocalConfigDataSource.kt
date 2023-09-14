package com.jgpl.weatherapp.data.source.local.userconfig

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.jgpl.weatherapp.data.source.UserConfigDataSource
import com.jgpl.weatherapp.data.source.local.userconfig.dto.UserConfigField
import com.jgpl.weatherapp.data.source.local.userconfig.mapper.UserConfigLocalMapper
import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.model.CelsiusName
import com.jgpl.weatherapp.domain.model.KmphName
import com.jgpl.weatherapp.domain.model.MillimeterName
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.model.defaultCity
import com.jgpl.weatherapp.utils.AppResult
import com.jgpl.weatherapp.utils.resultFailure
import com.jgpl.weatherapp.utils.resultSuccess
import com.jgpl.weatherapp.utils.resultSuccessEmpty
import kotlinx.coroutines.flow.first

class LocalConfigDataSource(
    private val dataStore: DataStore<Preferences>,
    private val mapper: UserConfigLocalMapper
) : UserConfigDataSource {

    override suspend fun getUserConfig(): AppResult<UserConfigModel, AppError> {
        val config = dataStore.data.first()

        return try {
            val city = config[UserConfigField.City.field] ?: defaultCity
            val temperature =
                mapper.mapTemperature(config[UserConfigField.Temperature.field] ?: CelsiusName)
            val speed = mapper.mapSpeed(config[UserConfigField.Speed.field] ?: KmphName)
            val volume = mapper.mapVolume(config[UserConfigField.Volume.field] ?: MillimeterName)

            val userConfig = UserConfigModel(
                city = city,
                temperature = temperature,
                speed = speed,
                volume = volume
            )
            resultSuccess(userConfig)
        } catch (e: Exception) {
            resultFailure(AppError.Unknown)
        }
    }

    override suspend fun setUserConfig(userConfig: UserConfigModel): AppResult<Unit, AppError> {
        return try {
            dataStore.edit { config ->
                with(userConfig) {
                    config[UserConfigField.City.field] = city
                    config[UserConfigField.Temperature.field] = temperature.value
                    config[UserConfigField.Speed.field] = speed.value
                    config[UserConfigField.Volume.field] = volume.value
                }
            }
            resultSuccessEmpty()
        } catch (e: Exception) {
            resultFailure(AppError.Unknown)
        }
    }

}