package com.example.habitapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.habitapp.data.room.UserEvent
import com.example.habitapp.data.room.UserState

@Composable
fun RegisterForm(
    state: UserState,
    onEvent: (UserEvent) -> Unit,
    modifier: Modifier

) { Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
    TextField(
        value = state.firstName,
        onValueChange = {
            onEvent(UserEvent.SetFirstName(it))
        },
        placeholder = {
            Text(text = "First name")
        }
    )
    TextField(
        value = state.surname,
        onValueChange = {
            onEvent(UserEvent.SetSurname(it))
        },
        placeholder = {
            Text(text = "Surname")
        }
    )
    TextField(
        value = state.telNo,
        onValueChange = {
            onEvent(UserEvent.SetTelNo(it))
        },
        placeholder = {
            Text(text = "TelNo")
        }
    )
    Button(onClick = {
        onEvent(UserEvent.SaveUser)
    }) {
        Text(text = "Save")
    }

}

}