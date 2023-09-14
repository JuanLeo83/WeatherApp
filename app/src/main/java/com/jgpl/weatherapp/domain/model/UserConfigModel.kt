package com.jgpl.weatherapp.domain.model

data class UserConfigModel(
    val city: String,
    val temperature: TemperatureOption,
    val speed: SpeedOption,
    val volume: VolumeOption
)
const val defaultCity = "London"

sealed class TemperatureOption(val value: String) {
    object Celsius : TemperatureOption(CelsiusName)
    object Fahrenheit : TemperatureOption(FahrenheitName)
}
const val CelsiusName = "Celsius"
const val FahrenheitName = "Fahrenheit"
val temperatureOptions = listOf(
    TemperatureOption.Celsius,
    TemperatureOption.Fahrenheit
)

sealed class SpeedOption(val value: String) {
    object Kmph : SpeedOption(KmphName)
    object Mph : SpeedOption(MphName)
}
const val KmphName = "Km/h"
const val MphName = "Mph"
val speedOptions = listOf(
    SpeedOption.Kmph,
    SpeedOption.Mph
)

sealed class VolumeOption(val value: String) {
    object Millimeter : VolumeOption(MillimeterName)
    object Inch : VolumeOption(InchName)
}
const val MillimeterName = "mm"
const val InchName = "inch"
val volumeOptions = listOf(
    VolumeOption.Millimeter,
    VolumeOption.Inch
)