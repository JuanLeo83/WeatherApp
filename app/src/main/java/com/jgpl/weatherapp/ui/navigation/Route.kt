package com.jgpl.weatherapp.ui.navigation

sealed class Route(val name: String) {
    data object CurrentScreen : Route("main")
    data object SettingsScreen : Route("settings")
}
