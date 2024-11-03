package com.jgpl.weatherapp.ui.screen.current

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.jgpl.weatherapp.ui.screen.current.component.CurrentExtraInfoComponent
import com.jgpl.weatherapp.ui.screen.current.component.CurrentWeatherComponent
import com.jgpl.weatherapp.ui.screen.current.component.DateComponent
import com.jgpl.weatherapp.ui.screen.current.component.HourForecastComponent
import com.jgpl.weatherapp.ui.screen.current.component.LocationComponent
import com.jgpl.weatherapp.ui.screen.current.component.NextDayListComponent
import com.jgpl.weatherapp.ui.screen.current.vo.CurrentVo
import com.jgpl.weatherapp.ui.theme.CurrentScreenColorEnd
import com.jgpl.weatherapp.ui.theme.CurrentScreenColorStart
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrentScreen(onClickSettingsAction: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(CurrentScreenColorStart)
    val interactionSource = remember { MutableInteractionSource() }

    val viewModel: CurrentViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        LoadingComponent()
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            CurrentScreenColorStart,
                            CurrentScreenColorEnd
                        )
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = stringResource(id = R.string.current_screen_settings_button_content_description),
                colorFilter = ColorFilter.tint(color = Color.White),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(12.dp)
                    .size(32.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onClickSettingsAction() }
            )
            Column(
                modifier = Modifier.scrollable(
                    state = rememberScrollableState(consumeScrollDelta = { 1f }),
                    orientation = Orientation.Vertical
                )
            ) {
                HeaderComponent(current = state.currentVo)
                ExtraInfoSectionComponent(current = state.currentVo)
                HourForecastComponent(hourList = state.currentVo.todayHourForecast)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.current_screen_daily_forecast_label),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
                Spacer(modifier = Modifier.height(0.dp))
                NextDayListComponent(nextDays = state.currentVo.nextDaysForecast)
            }
        }
    }
}

@Composable
private fun HeaderComponent(current: CurrentVo) {
    Column(
        Modifier.padding(bottom = 15.dp)
    ) {
        LocationComponent(locationName = current.cityName, size = 2f)

        DateComponent(date = current.date, size = 1.4f)

        CurrentWeatherComponent(
            temperature = current.temperature,
            condition = current.conditionText,
            iconUrl = current.conditionIcon
        )
    }
}

@Composable
fun ExtraInfoSectionComponent(current: CurrentVo) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        CurrentExtraInfoComponent(extraInfo = current.precipitation)
        CurrentExtraInfoComponent(extraInfo = current.humidity)
        CurrentExtraInfoComponent(
            extraInfo = current.windSpeed,
            isWindDirection = true,
            rotation = current.windDirection.windDegrees
        )
    }
}