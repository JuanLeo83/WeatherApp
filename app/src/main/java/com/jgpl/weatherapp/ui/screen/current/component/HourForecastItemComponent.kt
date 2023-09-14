package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgpl.weatherapp.R
import com.jgpl.weatherapp.ui.screen.current.vo.TodayHourForecastItemVo

@Composable
fun HourForecastItemComponent(
    hour: TodayHourForecastItemVo
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(color = Color(0x22FFFFFF))
            .defaultMinSize(minWidth = 50.dp)
            .border(if (hour.isCurrentHour) BorderStroke(2.dp, Color.White) else BorderStroke(0.dp, Color.Transparent))
            .height(120.dp)
            .padding(8.dp)
    ) {
        Text(
            text = hour.hour,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = hour.temperature,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))

        Image(
            painterResource(id = hour.icon),
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier.size(24.dp),
            contentDescription = stringResource(R.string.current_screen_hour_condition_image_content_description, hour.conditionText)
        )
    }
}

@Preview
@Composable
private fun HourForecastItemComponentPreview() {
    HourForecastItemComponent(
        TodayHourForecastItemVo(
            "23:00",
            "24º",
            R.drawable.ic_mostly_cloudy,
            "Cloudy",
            true
        )
    )
}

@Preview
@Composable
private fun HourForecastItemComponentPreview2() {
    HourForecastItemComponent(
        TodayHourForecastItemVo(
            "9:00",
            "24º",
            R.drawable.ic_mostly_cloudy,
            "Cloudy"
        )
    )
}