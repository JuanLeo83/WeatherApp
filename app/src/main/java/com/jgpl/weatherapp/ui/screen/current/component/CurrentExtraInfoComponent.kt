package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jgpl.weatherapp.R
import com.jgpl.weatherapp.ui.screen.current.vo.ExtraInfoVo

@Composable
fun CurrentExtraInfoComponent(
    extraInfo: ExtraInfoVo,
    isWindDirection: Boolean = false,
    rotation: Int = 0
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .defaultMinSize(minWidth = 120.dp)
            .background(color = Color(0x22FFFFFF))
            .padding(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = extraInfo.icon),
                contentDescription = extraInfo.contentDescription,
                colorFilter = ColorFilter.tint(color = Color.White),
                modifier = Modifier.size(32.dp)
            )
            if (isWindDirection) {
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = extraInfo.contentDescription,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(rotation.toFloat())
                )
            }
        }
        Text(
            text = extraInfo.value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = extraInfo.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun CurrentExtraInfoComponentPreview() {
    CurrentExtraInfoComponent(
        ExtraInfoVo(
            icon = R.drawable.ic_umbrella, value = "24.22 km/h",
            "Humidity",
            "Umbrella"
        )
    )
}