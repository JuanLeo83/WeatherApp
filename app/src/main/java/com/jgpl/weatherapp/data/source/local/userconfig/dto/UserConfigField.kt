package com.jgpl.weatherapp.data.source.local.userconfig.dto

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

sealed class UserConfigField(val field: Preferences.Key<String>) {
    object City : UserConfigField(stringPreferencesKey("city"))
    object Temperature : UserConfigField(stringPreferencesKey("temperature"))
    object Speed : UserConfigField(stringPreferencesKey("speed"))
    object Volume : UserConfigField(stringPreferencesKey("volume"))
}
