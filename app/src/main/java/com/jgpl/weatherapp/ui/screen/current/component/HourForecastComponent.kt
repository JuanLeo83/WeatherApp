package com.jgpl.weatherapp.ui.screen.current.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jgpl.weatherapp.R
import com.jgpl.weatherapp.ui.screen.current.vo.TodayHourForecastItemVo
import kotlinx.coroutines.launch

@Composable
fun HourForecastComponent(
    hourList: List<TodayHourForecastItemVo>
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {

        coroutineScope.launch {
            if (hourList.isEmpty()) return@launch
            val index = hourList.indexOfFirst { item -> item.isCurrentHour }
            listState.scrollToItem(index)
        }

        items(hourList) { hour ->
            HourForecastItemComponent(hour = hour)
        }
    }
}

@Preview
@Composable
private fun HourForecastComponentPreview() {
    HourForecastComponent(
        listOf(
            TodayHourForecastItemVo(
                "9:00",
                "24º",
                R.drawable.ic_mostly_cloudy,
                "Cloudy"
            ),
            TodayHourForecastItemVo(
                "10:00",
                "25º",
                R.drawable.ic_mostly_cloudy,
                "Cloudy"
            ),
            TodayHourForecastItemVo(
                "11:00",
                "26º",
                R.drawable.ic_sun,
                "Sunny"
            )
        )
    )
}