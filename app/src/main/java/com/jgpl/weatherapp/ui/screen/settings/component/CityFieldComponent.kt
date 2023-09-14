package com.jgpl.weatherapp.ui.screen.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jgpl.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityFieldComponent(
    modifier: Modifier = Modifier,
    label: String,
    query: String,
    isLoading: Boolean,
    isFinished: Boolean,
    onFinishedAction: () -> Unit,
    onDoneAction: () -> Unit,
    onClickClearAction: () -> Unit,
    onQueryChangeAction: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    var isFocused by remember { mutableStateOf(false) }
    var showClearButton by remember { mutableStateOf(false) }

    if (isFinished) {
        focusManager.clearFocus()
        onFinishedAction()
    }

    showClearButton = isFocused && query.isNotEmpty()

    Column {
        OutlinedTextField(
            value = query,
            onValueChange = { onQueryChangeAction(it) },
            label = { Text(text = label) },
            trailingIcon = {
                if (showClearButton) {
                    IconButton(onClick = { onClickClearAction() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.settings_city_clear_button_content_description)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onDone = { onDoneAction() }),
            singleLine = true,
            modifier = modifier.onFocusChanged { isFocused = it.isFocused }
        )
        if (isLoading) {
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}