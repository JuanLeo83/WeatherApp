package com.jgpl.weatherapp.domain.model

data class WindModel(
    val direction: String,
    val degrees: Int,
    val speedMiles: Float,
    val speedKilometers: Float
)