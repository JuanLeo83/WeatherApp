package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import com.jgpl.weatherapp.R

@Composable
fun LocationComponent(locationName: String, size: Float = 1f) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = stringResource(id = R.string.current_screen_location_icon_content_description),
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier.size((18 * size).dp)
        )
        Text(
            text = locationName,
            fontWeight = FontWeight.Bold,
            fontSize = (14 * size).sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    }
}

@Preview
@Composable
private fun LocationComponentPreview() {
    LocationComponent("London", size= 2f)
}