package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DateComponent(date: String, size: Float = 1f) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = date,
            fontSize = (14 * size).sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun DateComponentPreview() {
    DateComponent("Thu, 06 July 2023")
}