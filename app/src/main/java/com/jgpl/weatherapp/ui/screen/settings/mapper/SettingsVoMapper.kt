package com.jgpl.weatherapp.ui.screen.settings.mapper

import com.jgpl.weatherapp.domain.model.SpeedOption
import com.jgpl.weatherapp.domain.model.TemperatureOption
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.model.VolumeOption
import com.jgpl.weatherapp.domain.model.speedOptions
import com.jgpl.weatherapp.domain.model.temperatureOptions
import com.jgpl.weatherapp.domain.model.volumeOptions
import com.jgpl.weatherapp.ui.screen.settings.component.OptionItemVo
import com.jgpl.weatherapp.ui.screen.settings.vo.SettingsVo

class SettingsVoMapper {

    fun mapToVo(model: UserConfigModel): SettingsVo {

        return SettingsVo(
            city = model.city,
            speed = model.speed.value,
            volume = model.volume.value,
            temperature = model.temperature.value
        )
    }

    fun mapToModel(vo: SettingsVo): UserConfigModel {
        return UserConfigModel(
            city = vo.city,
            speed = mapSpeed(vo.speed),
            volume = mapVolume(vo.volume),
            temperature = mapTemperature(vo.temperature)
        )
    }

    fun mapSpeedOptionsToVo(): List<OptionItemVo<String>> {
        return speedOptions.map {
            OptionItemVo(
                text = it.value,
                option = it.value
            )
        }
    }

    fun mapVolumeOptionsToVo(): List<OptionItemVo<String>> {
        return volumeOptions.map {
            OptionItemVo(
                text = it.value,
                option = it.value
            )
        }
    }

    fun mapTemperatureOptionsToVo(): List<OptionItemVo<String>> {
        return temperatureOptions.map {
            OptionItemVo(
                text = it.value,
                option = it.value
            )
        }
    }

    private fun mapSpeed(value: String): SpeedOption {
        return speedOptions.find { it.value == value } ?: SpeedOption.Kmph
    }

    private fun mapVolume(value: String): VolumeOption {
        return volumeOptions.find { it.value == value } ?: VolumeOption.Millimeter
    }

    private fun mapTemperature(value: String): TemperatureOption {
        return temperatureOptions.find { it.value == value } ?: TemperatureOption.Celsius
    }
}