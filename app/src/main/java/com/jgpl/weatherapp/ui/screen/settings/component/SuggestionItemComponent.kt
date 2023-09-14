package com.jgpl.weatherapp.ui.screen.settings.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jgpl.weatherapp.ui.screen.settings.vo.SuggestionVo


@Composable
fun SuggestionItemComponent(
    suggestion: SuggestionVo,
    onClickAction: (SuggestionVo) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clickable { onClickAction(suggestion) }
    ) {
        Text(text = suggestion.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = suggestion.region, fontSize = 12.sp)
            Text(text = suggestion.country, fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
private fun SuggestionItemComponentPreview() {
    SuggestionItemComponent(
        suggestion = SuggestionVo(
            name = "London",
            region = "City of London, Greater London",
            country = "United Kingdom"
        )
    ) {}
}