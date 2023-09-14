package com.jgpl.weatherapp.ui.navigation

sealed class Route(val name: String) {
    object CurrentScreen : Route("main")
    object SettingsScreen : Route("settings")
}
