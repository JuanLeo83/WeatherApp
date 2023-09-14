package com.jgpl.weatherapp.ui.screen.settings.vo

import com.jgpl.weatherapp.R

data class LanguageVo(
    val code: String = "",
    val language: String = "",
    val flag: String = "",
    val contentDescription: Int = R.string.settings_language_option_content_description
)