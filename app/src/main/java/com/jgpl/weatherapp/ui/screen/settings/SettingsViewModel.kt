package com.jgpl.weatherapp.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgpl.weatherapp.domain.usecase.GetLocationsUseCase
import com.jgpl.weatherapp.domain.usecase.GetUserConfigUseCase
import com.jgpl.weatherapp.domain.usecase.SetUserConfigUseCase
import com.jgpl.weatherapp.ui.screen.settings.error.SettingsError
import com.jgpl.weatherapp.ui.screen.settings.mapper.SettingsVoMapper
import com.jgpl.weatherapp.ui.screen.settings.mapper.SuggestionMapper
import com.jgpl.weatherapp.ui.screen.settings.vo.SuggestionVo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getUserConfigUseCase: GetUserConfigUseCase,
    private val setUserConfigUseCase: SetUserConfigUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val settingsMapper: SettingsVoMapper,
    private val suggestionMapper: SuggestionMapper
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        getSettings()
    }

    private fun getSettings() {
        viewModelScope.launch {
            setLoading()
            getUserConfigUseCase()
                .onSuccess { result ->
                    val userConfig = settingsMapper.mapToVo(result)
                    _state.emit(
                        _state.value.copy(
                            saved = false,
                            isLoadingConfig = false,
                            settingsVo = userConfig,
                            query = userConfig.city,
                            speedOptions = settingsMapper.mapSpeedOptionsToVo(),
                            volumeOptions = settingsMapper.mapVolumeOptionsToVo(),
                            temperatureOptions = settingsMapper.mapTemperatureOptionsToVo(),
                            error = null
                        )
                    )
                }
                .onFailure { showError() }
        }
    }

    fun setSettings() {
        viewModelScope.launch {
            setLoading()
            val userConfig = settingsMapper.mapToModel(_state.value.settingsVo)
            setUserConfigUseCase(userConfig)
                .onSuccess {
                    _state.emit(
                        _state.value.copy(
                            saved = true,
                            isLoadingConfig = false,
                            error = null
                        )
                    )
                }
                .onFailure { showError() }
        }
    }

    private suspend fun setLoading() {
        if (_state.value.isLoadingConfig) return

        _state.emit(_state.value.copy(isLoadingConfig = true))
    }

    private suspend fun showError() {
        _state.emit(
            _state.value.copy(
                isLoadingConfig = false,
                error = SettingsError.Unknown
            )
        )
    }

    fun setQuery(query: String) {
        modifyQueryState(query)
    }

    fun clearQuery() {
        modifyQueryState("")
        viewModelScope.launch {
            _state.emit(_state.value.copy(suggestionsVo = listOf()))
        }
    }

    private fun modifyQueryState(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(query = value, isSuggestionSelected = false))

            if (_state.value.query.isEmpty()) return@launch

            getLocationsUseCase(_state.value.query)
                .onSuccess { result ->
                    val suggestions = suggestionMapper.map(result)
                    _state.emit(
                        _state.value.copy(
                            isLoadingSuggestions = false,
                            suggestionsVo = suggestions
                        )
                    )
                }
                .onFailure { showError() }
        }
    }

    fun onSelectSuggestion(suggestion: SuggestionVo) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    settingsVo = _state.value.settingsVo.copy(
                        city = suggestion.name
                    ),
                    suggestionsVo = listOf(),
                    query = suggestion.name,
                    isSuggestionSelected = true
                )
            )
        }
    }

    fun resetSuggestionSelected() {
        setSuggestionSelected(false)
    }

    fun suggestionHasBeenSelected() {
        setSuggestionSelected(true)
    }

    private fun setSuggestionSelected(value: Boolean) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    isSuggestionSelected = value
                )
            )
        }
    }

    fun setSpeed(speed: String) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    settingsVo = _state.value.settingsVo.copy(
                        speed = speed
                    ),
                    isSuggestionSelected = true
                )
            )
        }
    }

    fun setVolume(volume: String) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    settingsVo = _state.value.settingsVo.copy(
                        volume = volume
                    ),
                    isSuggestionSelected = true
                )
            )
        }
    }

    fun setTemperature(temperature: String) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    settingsVo = _state.value.settingsVo.copy(
                        temperature = temperature
                    ),
                    isSuggestionSelected = true
                )
            )
        }
    }

}