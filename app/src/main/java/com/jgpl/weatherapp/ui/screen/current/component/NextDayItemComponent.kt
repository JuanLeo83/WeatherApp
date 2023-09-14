package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import com.jgpl.weatherapp.ui.screen.current.vo.NextDayVo

@Composable
fun NextDayItemComponent(
    nextDay: NextDayVo
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0x22FFFFFF))
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        Text(
            text = nextDay.date,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.defaultMinSize(minWidth = 150.dp)
        )
        Text(
            text = "${nextDay.minTemperature} | ${nextDay.maxTemperature}",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = nextDay.icon),
            contentDescription = stringResource(id = R.string.current_screen_next_day_condition_image_content_description, nextDay.conditionText),
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun NextDayItemComponentPreview() {
    NextDayItemComponent(
        NextDayVo(
            date = "Wed, 3 Dec",
            maxTemperature = "6º",
            minTemperature = "-8º",
            icon = R.drawable.ic_sun,
            conditionText = "Sunny"
        )
    )
}