package com.jgpl.weatherapp.ui.screen.settings.vo

data class SuggestionVo(
    val name: String,
    val region: String,
    val country: String
) {
    fun getFullName(): String = "$name, $region - $country"
}
