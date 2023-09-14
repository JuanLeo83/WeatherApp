package com.jgpl.weatherapp.ui.screen.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.usecase.GetLocationsUseCase
import com.jgpl.weatherapp.domain.usecase.GetUserConfigUseCase
import com.jgpl.weatherapp.domain.usecase.SetUserConfigUseCase
import com.jgpl.weatherapp.ui.screen.settings.mapper.SettingsErrorMapper
import com.jgpl.weatherapp.ui.screen.settings.mapper.SettingsVoMapper
import com.jgpl.weatherapp.ui.screen.settings.mapper.SuggestionMapper
import com.jgpl.weatherapp.ui.screen.settings.vo.LanguageVo
import com.jgpl.weatherapp.ui.screen.settings.vo.SuggestionVo
import com.jgpl.weatherapp.utils.onFailure
import com.jgpl.weatherapp.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Locale

class SettingsViewModel(
    private val getUserConfigUseCase: GetUserConfigUseCase,
    private val setUserConfigUseCase: SetUserConfigUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val settingsMapper: SettingsVoMapper,
    private val suggestionMapper: SuggestionMapper,
    private val errorMapper: SettingsErrorMapper
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        getSettings()
    }

    private fun getSettings() {
        getUserConfigUseCase.prepare(Unit)
            .onStart { setLoading() }
            .onEach {
                it.onSuccess { result ->
                    val userConfig = settingsMapper.mapToVo(result)
                    _state.emit(
                        _state.value.copy(
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
                it.onFailure { error ->
                    showError(error)
                }
            }
            .launchIn(viewModelScope)
    }

    fun setSettings() {
        val userConfig = settingsMapper.mapToModel(_state.value.settingsVo)
        setUserConfigUseCase.prepare(userConfig)
            .onStart { setLoading() }
            .onEach {
                it.onSuccess { _ ->
                    _state.emit(
                        _state.value.copy(
                            isLoadingConfig = false,
                            error = null
                        )
                    )
                }
                it.onFailure { error ->
                    showError(error)
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun setLoading() {
        if (_state.value.isLoadingConfig) return

        _state.emit(_state.value.copy(isLoadingConfig = true))
    }

    private suspend fun showError(error: AppError) {
        val settingsError = errorMapper.map(error)
        _state.emit(
            _state.value.copy(
                isLoadingConfig = false,
                error = settingsError
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
        }

        if (_state.value.query.isEmpty()) return

        getLocationsUseCase.prepare(_state.value.query)
            .onStart { _state.emit(_state.value.copy(isLoadingSuggestions = true)) }
            .onEach {
                it.onSuccess { result ->
                    val suggestions = suggestionMapper.map(result)
                    _state.emit(
                        _state.value.copy(
                            isLoadingSuggestions = false,
                            suggestionsVo = suggestions
                        )
                    )
                }
                it.onFailure { error ->
                    showError(error)
                }
            }
            .launchIn(viewModelScope)
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