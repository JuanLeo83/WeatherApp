package com.jgpl.weatherapp.ui.screen.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgpl.weatherapp.domain.error.AppError
import com.jgpl.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.jgpl.weatherapp.ui.screen.current.mapper.CurrentErrorMapper
import com.jgpl.weatherapp.ui.screen.current.mapper.CurrentVoMapper
import com.jgpl.weatherapp.utils.onFailure
import com.jgpl.weatherapp.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CurrentViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val mapper: CurrentVoMapper,
    private val errorMapper: CurrentErrorMapper
) : ViewModel() {

    private val _state = MutableStateFlow(CurrentState())
    val state = _state.asStateFlow()

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        getCurrentWeatherUseCase.prepare(Unit)
            .onStart { showLoading() }
            .onEach {
                it.onSuccess { result ->
                    val todayVo = mapper.map(result)
                    _state.emit(_state.value.copy(
                        isLoading = false,
                        currentVo = todayVo,
                        error = null
                    ))
                }
                it.onFailure { error ->
                    showError(error)
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun showLoading() {
        if (_state.value.isLoading) return

        _state.emit(_state.value.copy(isLoading = true))
    }

    private suspend fun showError(error: AppError) {
        val currentError = errorMapper.map(error)
        _state.emit(_state.value.copy(
            isLoading = false,
            error = currentError
        ))
    }

}