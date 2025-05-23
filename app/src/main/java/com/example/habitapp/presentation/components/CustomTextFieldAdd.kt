package com.example.habitapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextFieldAdd(
    focusManager: FocusManager,
    hintText: String,
    text: String,
    onNameChange: (String) -> Unit,
    errorMessage: String,
    errorPresent: Boolean,
    isNumeric: Boolean = false
) {
    Surface(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { input ->
                val trimmedInput = input.trim()
                if (isNumeric) {
                    if (trimmedInput.all { it.isDigit() }) {
                        onNameChange(trimmedInput)
                    }
                } else {
                    onNameChange(trimmedInput)
                }
            },
            isError = errorPresent,
            singleLine = true,
            label = {
                Text(hintText)
            },
            modifier = Modifier.semantics { contentDescription = hintText },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = if (isNumeric) KeyboardType.Number else KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Text(
            modifier = Modifier.padding(10.dp).semantics { contentDescription = text },
            text = if (errorPresent) "" else errorMessage,
            fontSize = 14.sp,
            color = Color.Red
        )
    }
}