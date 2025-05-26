package com.example.habitapp.presentation.screens.landingScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.example.habitapp.R

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
            navigateToLoginScreen: () -> Unit,
navigateToRegisterScreen: () -> Unit,
//    navigateToHomeScreen: () -> Unit
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
                        text = stringResource(R.string.LandingScreenText1),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = stringResource(R.string.LandingScreenText2),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Button(
                        onClick = {
                            navigateToLoginScreen()
                        }
                    ) {
                        Text(stringResource(R.string.login))
                    }
                    Button(
                        onClick = {
                            navigateToRegisterScreen()
                        }
                    ) {
                        Text(stringResource(R.string.register))
                    }
                }
            }
        }
    )
}