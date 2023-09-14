package com.jgpl.weatherapp.ui.screen.current.vo

import com.jgpl.weatherapp.R

data class ExtraInfoVo(
    val icon: Int = R.drawable.ic_thunderbolt,
    val value: String = "",
    val label: String = "",
    val contentDescription: String = "",
    val windDegrees: Int = 0
)
