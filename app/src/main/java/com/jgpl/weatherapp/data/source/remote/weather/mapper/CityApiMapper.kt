package com.jgpl.weatherapp.data.source.remote.weather.mapper

import com.jgpl.weatherapp.data.source.remote.weather.dto.PlaceDTO
import com.jgpl.weatherapp.domain.model.CityModel

class CityApiMapper {

    fun map(places: List<PlaceDTO>): List<CityModel> {
        return places.map { place ->
            CityModel(
                name = place.name,
                region = place.region,
                country = place.country
            )
        }
    }
}