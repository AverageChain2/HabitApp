package com.example.habitapp.presentation.screens.landingScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
            navigateToLoginScreen: () -> Unit,
navigateToRegisterScreen: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Welcome to HabitApp",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Your journey starts here",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Button(
                        onClick = {
                            navigateToLoginScreen()
                        }
                    ) {
                        Text(text = "Login")
                    }
                    Button(
                        onClick = {
                            navigateToRegisterScreen()
                        }
                    ) {
                        Text(text = "Register")
                    }
                }
            }
        }
    )
}