package com.jgpl.weatherapp.ui.screen.current.mapper

import android.content.Context
import com.jgpl.weatherapp.R
import com.jgpl.weatherapp.domain.model.CurrentWeatherModel
import com.jgpl.weatherapp.domain.model.HourForecastModel
import com.jgpl.weatherapp.domain.model.NextDayModel
import com.jgpl.weatherapp.domain.model.PrecipitationModel
import com.jgpl.weatherapp.domain.model.SpeedOption
import com.jgpl.weatherapp.domain.model.TemperatureModel
import com.jgpl.weatherapp.domain.model.TemperatureOption
import com.jgpl.weatherapp.domain.model.UserConfigModel
import com.jgpl.weatherapp.domain.model.VolumeOption
import com.jgpl.weatherapp.domain.model.WindModel
import com.jgpl.weatherapp.ui.screen.current.vo.CurrentVo
import com.jgpl.weatherapp.ui.screen.current.vo.ExtraInfoVo
import com.jgpl.weatherapp.ui.screen.current.vo.NextDayVo
import com.jgpl.weatherapp.ui.screen.current.vo.TodayHourForecastItemVo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CurrentVoMapper(
    private val conditionMapper: ConditionMapper,
    private val context: Context
) {

    fun map(current: CurrentWeatherModel, config: UserConfigModel): CurrentVo {
        return CurrentVo(
            cityName = current.name,
            date = getFormattedDate(),
            temperature = getFormattedTemperature(
                current.temperature,
                config.temperature
            ),
            conditionIcon = getFormattedIconUrl(current.condition.icon),
            conditionText = current.condition.text,
            humidity = mapHumidity(current.humidity),
            precipitation = mapPrecipitation(current.precipitation, config.volume),
            windSpeed = mapWindSpeed(current.wind, config.speed),
            windDirection = mapWindDirection(current.wind),
            todayHourForecast = mapHourForecast(current.todayHourForecast, config),
            nextDaysForecast = mapNextDaysForecast(current.nextDaysForecast, config)
        )
    }

    private fun getFormattedDate(
        date: Date = Date(),
        format: String = "EEE, dd MMMM yyyy"
    ): String {
        return SimpleDateFormat(format, Locale.getDefault()).format(date)
    }

    private fun getFormattedTemperature(
        temperature: TemperatureModel,
        config: TemperatureOption
    ): String {
        return when (config) {
            is TemperatureOption.Celsius -> "${temperature.celsius.toInt()}º"
            is TemperatureOption.Fahrenheit -> "${temperature.fahrenheit.toInt()}º"
        }
    }

    private fun getFormattedIconUrl(icon: String): String {
        return "https:${icon.replace("64x64", "128x128")}"
    }

    private fun mapPrecipitation(
        precipitation: PrecipitationModel,
        config: VolumeOption
    ): ExtraInfoVo {
        val value = when (config) {
            is VolumeOption.Millimeter -> "${precipitation.mm} ${config.value}"
            is VolumeOption.Inch -> "${precipitation.inch} ${config.value}"
        }

        return ExtraInfoVo(
            icon = R.drawable.ic_umbrella,
            value = value,
            label = context.getString(R.string.current_screen_precipitation_label),
            contentDescription = context.getString(R.string.current_screen_precipitation_content_description)
        )
    }

    private fun mapHumidity(value: Int): ExtraInfoVo {
        return ExtraInfoVo(
            icon = R.drawable.ic_drop,
            value = "$value%",
            label = context.getString(R.string.current_screen_humidity_label),
            contentDescription = context.getString(R.string.current_screen_humidity_content_description)
        )
    }

    private fun mapWindSpeed(wind: WindModel, config: SpeedOption): ExtraInfoVo {
        val value = when (config) {
            is SpeedOption.Kmph -> "${wind.speedKilometers} ${config.value}"
            is SpeedOption.Mph -> "${wind.speedMiles} ${config.value}"
        }

        return ExtraInfoVo(
            icon = R.drawable.ic_wind,
            value = value,
            label = context.getString(R.string.current_screen_wind_label),
            contentDescription = context.getString(R.string.current_screen_wind_content_description)
        )
    }

    private fun mapWindDirection(value: WindModel): ExtraInfoVo {
        return ExtraInfoVo(
            icon = R.drawable.ic_arrow,
            value = value.direction,
            label = context.getString(R.string.current_screen_wind_label),
            contentDescription = context.getString(R.string.current_screen_wind_content_description),
            windDegrees = value.degrees
        )
    }

    private fun mapHourForecast(
        hourForecast: List<HourForecastModel>,
        config: UserConfigModel
    ): List<TodayHourForecastItemVo> {
        return hourForecast.map { item ->
            val hour = getHour(item.time)

            TodayHourForecastItemVo(
                hour = hour,
                temperature = getFormattedTemperature(item.temperature, config.temperature),
                icon = conditionMapper.getIcon(
                    conditionCode = item.condition.code,
                    isDay = item.isDay
                ),
                conditionText = item.condition.text,
                isCurrentHour = checkCurrentHour(hour)
            )
        }
    }

    private fun getHour(time: String): String {
        return time.split(" ")[1]
    }

    private fun checkCurrentHour(hour: String): Boolean {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val parsedHour = hour.split(":")[0].toInt()
        return currentHour == parsedHour
    }

    private fun mapNextDaysForecast(
        nextDays: List<NextDayModel>,
        config: UserConfigModel
    ): List<NextDayVo> {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return nextDays.map { nextDay ->
            NextDayVo(
                date = getFormattedDate(
                    dateFormatter.parse(nextDay.date) ?: Date(),
                    format = "EEE, dd MMM"
                ),
                maxTemperature = getFormattedTemperature(
                    nextDay.maxTemperature,
                    config.temperature
                ),
                minTemperature = getFormattedTemperature(
                    nextDay.minTemperature,
                    config.temperature
                ),
                icon = conditionMapper.getIcon(nextDay.condition.code, true),
                conditionText = nextDay.condition.text
            )
        }
    }

}