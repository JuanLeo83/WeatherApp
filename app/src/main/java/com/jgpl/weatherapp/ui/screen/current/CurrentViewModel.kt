package com.jgpl.weatherapp.ui.screen.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgpl.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.jgpl.weatherapp.domain.usecase.GetUserConfigUseCase
import com.jgpl.weatherapp.ui.screen.current.error.CurrentError
import com.jgpl.weatherapp.ui.screen.current.mapper.CurrentVoMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CurrentViewModel(
    private val getUserConfigUseCase: GetUserConfigUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val mapper: CurrentVoMapper
) : ViewModel() {

    private val _state = MutableStateFlow(CurrentState())
    val state = _state.asStateFlow()

    fun getCurrentWeather() {
        viewModelScope.launch {
            showLoading()
            getUserConfigUseCase()
                .onSuccess { config ->
                    getCurrentWeatherUseCase(config)
                        .onSuccess { current ->
                            val todayVo = mapper.map(current, config)
                            _state.emit(
                                _state.value.copy(
                                    isLoading = false,
                                    currentVo = todayVo,
                                    error = null
                                )
                            )
                        }
                        .onFailure { showError() }
                }
                .onFailure { showError() }
        }
    }

    private suspend fun showLoading() {
        if (_state.value.isLoading) return

        _state.emit(_state.value.copy(isLoading = true))
    }

    private suspend fun showError() {
        _state.emit(
            _state.value.copy(
                isLoading = false,
                error = CurrentError.Unknown
            )
        )
    }

}