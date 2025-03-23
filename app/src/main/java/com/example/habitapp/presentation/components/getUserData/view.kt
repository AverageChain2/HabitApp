package com.example.habitapp.presentation.components.getUserData

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun UserScreen(viewModel: UserViewModel, modifier: Modifier) {
    val userText by viewModel.userText.collectAsState() // Observe StateFlow
    Text(text = userText) // Display the fetched user info
}