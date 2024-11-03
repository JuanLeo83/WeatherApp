package com.jgpl.weatherapp.data.source.local.userconfig

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.jgpl.weatherapp.data.source.UserConfigDataSource
import com.jgpl.weatherapp.data.source.local.userconfig.dto.UserConfigField
import com.jgpl.weatherapp.data.source.local.userconfig.mapper.UserConfigLocalMapper
import com.jgpl.weatherapp.domain.model.CelsiusName
import com.jgpl.weatherapp.domain.model.KmphName
import com.jgpl.weatherapp.domain.model.MillimeterName
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.model.defaultCity
import kotlinx.coroutines.flow.first

class LocalConfigDataSource(
    private val dataStore: DataStore<Preferences>,
    private val mapper: UserConfigLocalMapper
) : UserConfigDataSource {

    override suspend fun getUserConfig(): Result<UserConfigModel> {
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
            Result.success(userConfig)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun setUserConfig(userConfig: UserConfigModel): Result<Unit> {
        return try {
            dataStore.edit { config ->
                with(userConfig) {
                    config[UserConfigField.City.field] = city
                    config[UserConfigField.Temperature.field] = temperature.value
                    config[UserConfigField.Speed.field] = speed.value
                    config[UserConfigField.Volume.field] = volume.value
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}