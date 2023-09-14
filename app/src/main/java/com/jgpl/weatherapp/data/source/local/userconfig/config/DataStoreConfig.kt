package com.jgpl.weatherapp.data.source.local.userconfig.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val userSettingsName = "UserSettings"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = userSettingsName)