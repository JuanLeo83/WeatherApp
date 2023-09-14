package com.jgpl.weatherapp.ui.screen.settings.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jgpl.weatherapp.ui.screen.settings.vo.SuggestionVo

@Composable
fun SuggestionListComponent(
    suggestions: List<SuggestionVo>,
    onSelectItemAction: (SuggestionVo) -> Unit
) {
    if (suggestions.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.35f)
        ) {
            items(suggestions) { suggestion ->
                SuggestionItemComponent(suggestion = suggestion, onClickAction = onSelectItemAction)
            }
        }
    }
}
