package com.jgpl.weatherapp.ui.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jgpl.weatherapp.R
import com.jgpl.weatherapp.ui.component.LoadingComponent
import com.jgpl.weatherapp.ui.screen.settings.component.CityFieldComponent
import com.jgpl.weatherapp.ui.screen.settings.component.SuggestionListComponent
import com.jgpl.weatherapp.ui.screen.settings.component.TwoOptionsSelectorComponent
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(onClickBackAction: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colorScheme.background)

    val viewModel: SettingsViewModel = getViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoadingConfig) {
        LoadingComponent()
    } else {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            SettingsToolbar(onClickBackAction)
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
            ) {
                Column(
                    modifier = Modifier.scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Vertical
                    )
                ) {
                    CityFieldComponent(
                        label = stringResource(id = R.string.settings_city_field_label),
                        query = state.query,
                        isLoading = state.isLoadingSuggestions,
                        isFinished = state.isSuggestionSelected,
                        onFinishedAction = { viewModel.resetSuggestionSelected() },
                        onDoneAction = { viewModel.suggestionHasBeenSelected() },
                        onClickClearAction = { viewModel.clearQuery() },
                        onQueryChangeAction = { viewModel.setQuery(it) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    SuggestionListComponent(suggestions = state.suggestionsVo) {
                        viewModel.onSelectSuggestion(it)
                    }

                    if (state.speedOptions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(32.dp))
                        TwoOptionsSelectorComponent(
                            label = stringResource(id = R.string.settings_speed_label),
                            optionLeft = state.speedOptions[0],
                            optionRight = state.speedOptions[1],
                            optionSelected = state.settingsVo.speed,
                            onOptionSelected = { viewModel.setSpeed(it) }
                        )
                    }

                    if (state.volumeOptions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(32.dp))
                        TwoOptionsSelectorComponent(
                            label = stringResource(id = R.string.settings_precipitation_label),
                            optionLeft = state.volumeOptions[0],
                            optionRight = state.volumeOptions[1],
                            optionSelected = state.settingsVo.volume,
                            onOptionSelected = { viewModel.setVolume(it) }
                        )
                    }

                    if (state.temperatureOptions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(32.dp))
                        TwoOptionsSelectorComponent(
                            label = stringResource(id = R.string.settings_temperature_label),
                            optionLeft = state.temperatureOptions[0],
                            optionRight = state.temperatureOptions[1],
                            optionSelected = state.settingsVo.temperature,
                            onOptionSelected = { viewModel.setTemperature(it) }
                        )
                    }
                }
                Button(
                    onClick = {
                        viewModel.setSettings()
                        onClickBackAction()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                ) {
                    Text(text = stringResource(id = R.string.settings_save_button))
                }
            }
        }
    }
}

@Composable
fun SettingsToolbar(onClickBackAction: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = stringResource(id = R.string.settings_back_button_content_description),
            colorFilter = ColorFilter.tint(color = if (isSystemInDarkTheme()) Color.White else Color.Black),
            modifier = Modifier
                .rotate(270f)
                .padding(8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onClickBackAction() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(R.string.settings_screen_title), fontSize = 24.sp)
    }
}