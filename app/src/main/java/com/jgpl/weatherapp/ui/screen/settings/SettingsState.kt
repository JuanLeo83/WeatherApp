package com.jgpl.weatherapp.ui.screen.settings

import com.jgpl.weatherapp.ui.screen.settings.component.OptionItemVo
import com.jgpl.weatherapp.ui.screen.settings.error.SettingsError
import com.jgpl.weatherapp.ui.screen.settings.vo.LanguageVo
import com.jgpl.weatherapp.ui.screen.settings.vo.SettingsVo
import com.jgpl.weatherapp.ui.screen.settings.vo.SuggestionVo

data class SettingsState(
    val isLoadingConfig: Boolean = false,
    val settingsVo: SettingsVo = SettingsVo(),
    val query: String = "",
    val isLoadingSuggestions: Boolean = false,
    val suggestionsVo: List<SuggestionVo> = listOf(),
    val isSuggestionSelected: Boolean = false,
    val speedOptions: List<OptionItemVo<String>> = listOf(),
    val volumeOptions: List<OptionItemVo<String>> = listOf(),
    val temperatureOptions: List<OptionItemVo<String>> = listOf(),
    val error: SettingsError? = null
)