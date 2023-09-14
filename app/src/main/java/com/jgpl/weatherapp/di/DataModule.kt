package com.jgpl.weatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.jgpl.weatherapp.data.repository.ConfigRepositoryImpl
import com.jgpl.weatherapp.data.repository.WeatherRepositoryImpl
import com.jgpl.weatherapp.data.source.local.userconfig.LocalConfigDataSource
import com.jgpl.weatherapp.data.source.UserConfigDataSource
import com.jgpl.weatherapp.data.source.local.userconfig.config.dataStore
import com.jgpl.weatherapp.data.source.local.userconfig.mapper.UserConfigLocalMapper
import com.jgpl.weatherapp.data.source.WeatherDataSource
import com.jgpl.weatherapp.data.source.remote.weather.RemoteWeatherDataSource
import com.jgpl.weatherapp.data.source.remote.weather.api.WeatherApi
import com.jgpl.weatherapp.data.source.remote.weather.config.ApiKey
import com.jgpl.weatherapp.data.source.remote.weather.mapper.CityApiMapper
import com.jgpl.weatherapp.data.source.remote.weather.mapper.WeatherApiMapper
import com.jgpl.weatherapp.domain.repository.ConfigRepository
import com.jgpl.weatherapp.domain.repository.WeatherRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {
    // REPOSITORIES
    single<ConfigRepository> { ConfigRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    // DATA SOURCES
    single { getDataStore(get()) }
    single { UserConfigLocalMapper() }
    single<UserConfigDataSource> { LocalConfigDataSource(get(), get()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { provideWeatherApi(get()) }
    single { getRemoteConfig() }
    single { provideApiKey(get()) }
    single { WeatherApiMapper() }
    single { CityApiMapper() }
    single<WeatherDataSource> { RemoteWeatherDataSource(get(), get(), get(), get()) }
}

private fun getDataStore(context: Context): DataStore<Preferences> = context.dataStore

private fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

private fun getRemoteConfig(): FirebaseRemoteConfig {
    val remoteConfig = Firebase.remoteConfig
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }
    remoteConfig.setConfigSettingsAsync(configSettings)
    remoteConfig.setDefaultsAsync(mapOf("weatherApiKey" to "1234abcdXYZ"))
    remoteConfig.fetchAndActivate()
    return remoteConfig
}

private fun provideApiKey(remoteConfig: FirebaseRemoteConfig): ApiKey {
    val key = remoteConfig.getString("weatherApiKey")
    return ApiKey(key)
}