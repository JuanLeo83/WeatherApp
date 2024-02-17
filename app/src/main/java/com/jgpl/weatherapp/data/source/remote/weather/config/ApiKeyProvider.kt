package com.jgpl.weatherapp.data.source.remote.weather.config

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await

interface ApiKeyProvider {
    suspend fun checkApiKey()
    fun getApiKey(): String
}

class ApiKeyProviderImpl(
    private val remoteConfig: FirebaseRemoteConfig
) : ApiKeyProvider {

    private lateinit var apiKey: String

    override suspend fun checkApiKey() {
        if (this::apiKey.isInitialized && apiKey.isNotEmpty()) return

        remoteConfig.fetchAndActivate().await()
        apiKey = remoteConfig.getString(weatherAPIKey)
    }

    override fun getApiKey() = apiKey

    companion object {
        private const val weatherAPIKey = "weatherApiKey"
    }

}