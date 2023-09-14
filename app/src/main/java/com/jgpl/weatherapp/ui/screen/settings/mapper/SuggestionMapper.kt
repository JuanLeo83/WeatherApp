package com.jgpl.weatherapp.ui.screen.settings.mapper

import com.jgpl.weatherapp.domain.model.CityModel
import com.jgpl.weatherapp.ui.screen.settings.vo.SuggestionVo

class SuggestionMapper {

    fun map(cities: List<CityModel>): List<SuggestionVo> {
        return cities.map { city ->
            SuggestionVo(
                name = city.name,
                region = city.region,
                country = city.country
            )
        }
    }
}