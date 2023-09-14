package com.jgpl.weatherapp.data.source.remote.weather.dto

import com.google.gson.annotations.SerializedName


data class PlaceDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val latitude: Float,
    @SerializedName("lon") val longitude: Float,
    @SerializedName("url") val url: String
)
