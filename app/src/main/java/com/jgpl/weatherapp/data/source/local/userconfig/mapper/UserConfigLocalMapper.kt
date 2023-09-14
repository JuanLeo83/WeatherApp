package com.jgpl.weatherapp.data.source.local.userconfig.mapper

import com.jgpl.weatherapp.domain.model.SpeedOption
import com.jgpl.weatherapp.domain.model.TemperatureOption
import com.jgpl.weatherapp.domain.model.VolumeOption
import com.jgpl.weatherapp.domain.model.speedOptions
import com.jgpl.weatherapp.domain.model.temperatureOptions
import com.jgpl.weatherapp.domain.model.volumeOptions

class UserConfigLocalMapper {

    fun mapTemperature(value: String): TemperatureOption {
        return temperatureOptions.find { it.value == value } ?: TemperatureOption.Celsius
    }

    fun mapSpeed(value: String): SpeedOption {
        return speedOptions.find { it.value == value } ?: SpeedOption.Kmph
    }

    fun mapVolume(value: String): VolumeOption {
        return volumeOptions.find { it.value == value } ?: VolumeOption.Millimeter
    }

}