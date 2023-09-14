package com.jgpl.weatherapp.data.source.remote.weather.dto

import com.google.gson.annotations.SerializedName

data class FullForecastDTO(
    @SerializedName("location") val location: LocationDTO,
    @SerializedName("current") val current: CurrentDTO,
    @SerializedName("forecast") val forecast: ForecastDTO
)

data class LocationDTO(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val latitude: Float,
    @SerializedName("lon") val longitude: Float,
    @SerializedName("tz_id") val tzId: String,
    @SerializedName("localtime_epoch") val localtimeEpoch: Long,
    @SerializedName("localtime") val localTime: String
)

data class CurrentDTO(
    @SerializedName("last_updated_epoch") val lastUpdateEpoch: Long,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val temperatureCelsius: Float,
    @SerializedName("temp_f") val temperatureFahrenheit: Float,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionDTO,
    @SerializedName("wind_mph") val windMph: Float,
    @SerializedName("wind_kph") val windKmph: Float,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("pressure_mb") val pressureMb: Float,
    @SerializedName("pressure_in") val pressureIn: Float,
    @SerializedName("precip_mm") val precipitationsMm: Float,
    @SerializedName("precip_in") val precipitationsIn: Float,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeCelsius: Float,
    @SerializedName("feelslike_f") val feelsLikeFahrenheit: Float,
    @SerializedName("vis_km") val visKm: Float,
    @SerializedName("vis_miles") val visMiles: Float,
    @SerializedName("uv") val uv: Float,
    @SerializedName("gust_mph") val gustMph: Float,
    @SerializedName("gust_kph") val gusKmph: Float,
    @SerializedName("air_quality") val airQuality: AirQualityDTO
)

data class ConditionDTO(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int
)

data class AirQualityDTO(
    @SerializedName("co") val co: Double,
    @SerializedName("no2") val no2: Double,
    @SerializedName("o3") val o3: Double,
    @SerializedName("so2") val so2: Double,
    @SerializedName("pm2_5") val pm2_5: Double,
    @SerializedName("pm10") val pm10: Double,
    @SerializedName("us-epa-index") val usEpaIndex: Int,
    @SerializedName("gb-defra-index") val gbDefraIndex: Int,
)

data class ForecastDTO(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayDTO>
)

data class ForecastDayDTO(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Long,
    @SerializedName("day") val day: DayDTO,
    @SerializedName("astro") val astro: AstroDTO,
    @SerializedName("hour") val hourList: List<HourDTO>
)

data class DayDTO(
    @SerializedName("maxtemp_c") val maxTempCelsius: Float,
    @SerializedName("maxtemp_f") val maxTempFahrenheit: Float,
    @SerializedName("mintemp_c") val minTempCelsius: Float,
    @SerializedName("mintemp_f") val minTempFahrenheit: Float,
    @SerializedName("avgtemp_c") val avgTempCelsius: Float,
    @SerializedName("avgtemp_f") val avgTempFahrenheit: Float,
    @SerializedName("maxwind_mph") val maxWindMph: Float,
    @SerializedName("maxwind_kph") val maxWindKph: Float,
    @SerializedName("totalprecip_mm") val totalPrecipitationsMm: Float,
    @SerializedName("totalprecip_in") val totalPrecipitationsIn: Float,
    @SerializedName("totalsnow_cm") val totalSnowCm: Float,
    @SerializedName("avgvis_km") val avgVisKm: Float,
    @SerializedName("avgvis_miles") val avgVisMiles: Float,
    @SerializedName("avghumidity") val avgHumidity: Float,
    @SerializedName("daily_will_it_rain") val dailyWillItRain: Int,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Int,
    @SerializedName("daily_will_it_snow") val dailyWillItSnow: Int,
    @SerializedName("daily_chance_of_snow") val dailyChanceOfSnow: Int,
    @SerializedName("condition") val condition: ConditionDTO,
    @SerializedName("uv") val uv: Float,
    @SerializedName("air_quality") val airQuality: AirQualityDTO,
    @SerializedName("astro") val astro: AstroDTO,
    @SerializedName("hour") val hour: HourDTO
)

data class AstroDTO(
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("moonrise") val moonrise: String,
    @SerializedName("moonset") val moonset: String,
    @SerializedName("moon_phase") val moonPhase: String,
    @SerializedName("moon_illumination") val moonIllumination: String,
    @SerializedName("is_moon_up") val isMoonUp: Int,
    @SerializedName("is_sun_up") val isSunUp: Int
)

data class HourDTO(
    @SerializedName("time_epoch") val timeEpoch: Long,
    @SerializedName("time") val time: String,
    @SerializedName("temp_c") val tempCelsius: Float,
    @SerializedName("temp_f") val tempFahrenheit: Float,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionDTO,
    @SerializedName("wind_mph") val windMph: Float,
    @SerializedName("wind_kph") val windKph: Float,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("pressure_mb") val pressureMb: Float,
    @SerializedName("pressure_in") val pressureIn: Float,
    @SerializedName("precip_mm") val precipMm: Float,
    @SerializedName("precip_in") val precipIn: Float,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeCelsius: Float,
    @SerializedName("feelslike_f") val feelLikeFahrenheit: Float,
    @SerializedName("windchill_c") val windChillCelsius: Float,
    @SerializedName("windchill_f") val windChillFahrenheit: Float,
    @SerializedName("heatindex_c") val heatIndexCelsius: Float,
    @SerializedName("heatindex_f") val heatIndexFahrenheit: Float,
    @SerializedName("dewpoint_c") val dewPointCelsius: Float,
    @SerializedName("dewpoint_f") val dewPointFahrenheit: Float,
    @SerializedName("will_it_rain") val willItRain: Int,
    @SerializedName("chance_of_rain") val chanceOfRain: Float,
    @SerializedName("will_it_snow") val willItSnow: Int,
    @SerializedName("chance_of_snow") val chanceOfSnow: Float,
    @SerializedName("vis_km") val visKm: Float,
    @SerializedName("vis_miles") val visMiles: Float,
    @SerializedName("gust_mph") val gustMph: Float,
    @SerializedName("gust_kph") val gustKph: Float,
    @SerializedName("uv") val uv: Float,
    @SerializedName("air_quality") val airQuality: AirQualityDTO
)