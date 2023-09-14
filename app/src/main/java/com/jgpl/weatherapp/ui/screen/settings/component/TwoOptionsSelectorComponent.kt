package com.jgpl.weatherapp.ui.screen.settings.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> TwoOptionsSelectorComponent(
    modifier: Modifier = Modifier,
    label: String,
    optionLeft: OptionItemVo<T>,
    optionRight: OptionItemVo<T>,
    optionSelected: String,
    onOptionSelected: (T) -> Unit
) {
    Column(modifier = modifier) {
        Text(text = label)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { onOptionSelected(optionLeft.option) },
                border = BorderStroke(
                    if (optionSelected == optionLeft.text) 3.dp else 1.dp,
                    MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 0,
                    bottomEndPercent = 0,
                    bottomStartPercent = 50
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = optionLeft.text)
            }
            OutlinedButton(
                onClick = { onOptionSelected(optionRight.option) },
                border = BorderStroke(
                    if (optionSelected == optionRight.text) 3.dp else 1.dp,
                    MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 0
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = optionRight.text)
            }
        }
    }
}

data class OptionItemVo<T>(
    val text: String,
    val option: T
)

@Preview
@Composable
private fun TwoOptionsSelectorComponentPreview() {
    TwoOptionsSelectorComponent(
        label = "Two options label",
        optionLeft = OptionItemVo(text = "Option left", "Left"),
        optionRight = OptionItemVo(text = "Option right", "Right"),
        optionSelected = "Option right"
    ) {}
}