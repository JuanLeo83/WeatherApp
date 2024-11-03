package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jgpl.weatherapp.R

@Composable
fun CurrentWeatherComponent(
    temperature: String,
    condition: String,
    iconUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AsyncImage(
                    model = iconUrl,
                    contentDescription = stringResource(
                        id = R.string.current_screen_condition_image_content_description,
                        condition
                    ),
                    modifier = Modifier.size(150.dp)
                )

                Text(
                    text = temperature,
                    fontSize = 100.sp,
                    color = Color.White
                )
            }

            Text(
                text = condition,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun TemperatureComponentPreview() {
    CurrentWeatherComponent(
        temperature = "5º",
        condition = "Cloudy",
        "http://cdn.weatherapi.com/weather/64x64/day/113.png"
    )
}