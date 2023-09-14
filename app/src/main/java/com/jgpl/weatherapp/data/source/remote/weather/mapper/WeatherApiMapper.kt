package com.jgpl.weatherapp.data.source.remote.weather.mapper

import com.jgpl.weatherapp.data.source.remote.weather.dto.ConditionDTO
import com.jgpl.weatherapp.data.source.remote.weather.dto.CurrentDTO
import com.jgpl.weatherapp.data.source.remote.weather.dto.DayDTO
import com.jgpl.weatherapp.data.source.remote.weather.dto.ForecastDayDTO
import com.jgpl.weatherapp.data.source.remote.weather.dto.FullForecastDTO
import com.jgpl.weatherapp.data.source.remote.weather.dto.HourDTO
import com.jgpl.weatherapp.domain.model.ConditionModel
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.model.HourForecastModel
import com.jgpl.weatherapp.domain.model.NextDayModel
import com.jgpl.weatherapp.domain.model.PrecipitationModel
import com.jgpl.weatherapp.domain.model.PressureModel
import com.jgpl.weatherapp.domain.model.TemperatureModel
import com.jgpl.weatherapp.domain.model.WindModel

class WeatherApiMapper {

    fun mapToModel(dto: FullForecastDTO): CurrentWeatherModel {
        return CurrentWeatherModel(
            name = dto.location.name,
            temperature = mapTemperature(dto.current),
            pressure = mapPressure(dto.current),
            condition = mapCondition(dto.current.condition),
            precipitation = mapPrecipitation(dto.current),
            humidity = dto.current.humidity,
            wind = mapWind(dto.current),
            todayHourForecast = mapCurrentDayHourForecast(dto.forecast.forecastDay),
            nextDaysForecast = mapNextDays(dto.forecast.forecastDay)
        )
    }

    private fun mapTemperature(current: CurrentDTO): TemperatureModel {
        return TemperatureModel(
            celsius = current.temperatureCelsius,
            fahrenheit = current.temperatureFahrenheit
        )
    }

    private fun mapWind(current: CurrentDTO): WindModel {
        return WindModel(
            direction = current.windDirection,
            degrees = current.windDegree,
            speedMiles = current.windMph,
            speedKilometers = current.windKmph
        )
    }

    private fun mapPressure(current: CurrentDTO): PressureModel {
        return PressureModel(
            millibars = current.pressureMb,
            psi = current.pressureIn
        )
    }

    private fun mapCondition(condition: ConditionDTO): ConditionModel {
        return ConditionModel(
            text = condition.text,
            icon = condition.icon,
            code = condition.code
        )
    }

    private fun mapPrecipitation(current: CurrentDTO): PrecipitationModel {
        return PrecipitationModel(
            mm = current.precipitationsMm,
            inch = current.precipitationsIn
        )
    }

    private fun mapCurrentDayHourForecast(forecastDay: List<ForecastDayDTO>): List<HourForecastModel> {
        return forecastDay.first().hourList.map { item ->
            HourForecastModel(
                time = item.time,
                temperature = mapTemperature(item),
                condition = mapCondition(item.condition),
                isDay = item.isDay == 1
            )
        }
    }

    private fun mapTemperature(hour: HourDTO): TemperatureModel {
        return TemperatureModel(
            celsius = hour.tempCelsius,
            fahrenheit = hour.tempFahrenheit
        )
    }

    private fun mapNextDays(nextDays: List<ForecastDayDTO>): List<NextDayModel> {
        val nextDaysMapped = mutableListOf<NextDayModel>()

        for (index in 1..2) {
            val forecastDay = nextDays[index]

            val nextDay = NextDayModel(
                date = forecastDay.date,
                minTemperature = mapMinTemperature(forecastDay.day),
                maxTemperature = mapMaxTemperature(forecastDay.day),
                condition = mapCondition(forecastDay.day.condition)
            )
            nextDaysMapped.add(nextDay)
        }

        return nextDaysMapped
    }

    private fun mapMinTemperature(day: DayDTO): TemperatureModel {
        return TemperatureModel(
            celsius = day.minTempCelsius,
            fahrenheit = day.minTempFahrenheit
        )
    }

    private fun mapMaxTemperature(day: DayDTO): TemperatureModel {
        return TemperatureModel(
            celsius = day.maxTempCelsius,
            fahrenheit = day.maxTempFahrenheit
        )
    }
}