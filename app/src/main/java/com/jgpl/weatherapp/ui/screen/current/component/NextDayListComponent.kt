package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgpl.weatherapp.R
import com.jgpl.weatherapp.ui.screen.current.vo.NextDayVo

@Composable
fun NextDayListComponent(
    nextDays: List<NextDayVo>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = false
    ) {
        items(nextDays) { nextDay ->
            NextDayItemComponent(nextDay = nextDay)
        }
    }
}

@Preview
@Composable
private fun NextDayListComponentPreview() {
    NextDayListComponent(
        listOf(
            NextDayVo(
                date = "Wed, 3 Dec",
                maxTemperature = "6º",
                minTemperature = "-8º",
                icon = R.drawable.ic_sun,
                conditionText = "Sunny"
            ),
            NextDayVo(
                date = "Wed, 3 Dec",
                maxTemperature = "6º",
                minTemperature = "-8º",
                icon = R.drawable.ic_sun,
                conditionText = "Sunny"
            ),
            NextDayVo(
                date = "Wed, 3 Dec",
                maxTemperature = "6º",
                minTemperature = "-8º",
                icon = R.drawable.ic_sun,
                conditionText = "Sunny"
            )
        )
    )
}