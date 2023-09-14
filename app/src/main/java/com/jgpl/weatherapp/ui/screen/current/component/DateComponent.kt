package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DateComponent(date: String, size: Float = 1f) {
    Text(
        text = date,
        fontSize = (14 * size).sp,
        color = Color.White
    )
}

@Preview
@Composable
private fun DateComponentPreview() {
    DateComponent("Thu, 06 July 2023")
}