package com.jgpl.weatherapp.ui.screen.current

import com.jgpl.weatherapp.ui.screen.current.error.CurrentError
import com.jgpl.weatherapp.ui.screen.current.vo.CurrentVo

data class CurrentState(
    val isLoading: Boolean = false,
    val currentVo: CurrentVo = CurrentVo(),
    val error: CurrentError? = null
)
