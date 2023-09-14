package com.jgpl.weatherapp.data.source.remote.weather.api

import com.jgpl.weatherapp.data.source.remote.weather.dto.FullForecastDTO
import com.jgpl.weatherapp.data.source.remote.weather.dto.PlaceDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
    fun getToday(
        @Query("key") key: String,
        @Query("q") place: String,
        @Query("lang") language: String,
        @Query("aqi") aqi: String = "yes",
        @Query("days") days: Int = 3
    ): Call<FullForecastDTO>

    @GET("search.json")
    fun getCities(
        @Query("key") key: String,
        @Query("q") query: String
    ): Call<List<PlaceDTO>>
}