package com.example.habitapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(text: String, clickButton: () -> Unit) {
    Button(
        onClick = clickButton,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
            .semantics{contentDescription = text},

    ) {
        Text(text=text, fontSize = 20.sp)
    }
}