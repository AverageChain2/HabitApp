package com.example.habitapp.presentation.screens.loginScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.habitapp.HabitApplication
import com.example.habitapp.data.model.Response
import com.example.habitapp.presentation.components.ProgressBar
import com.example.habitapp.presentation.screens.loginScreen.viewmodel.LoginViewModel


@Composable
fun LogIn(
    vm: LoginViewModel,
    showErrorMessage: (errorMessage: String?) -> Unit,
    navigateToHomeScreen: () -> Unit
) {
    when(val signInResponse = vm.signInResponse) {
        is Response.Startup -> Unit //Do nothing
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            if(HabitApplication.isRunningTest ||
                  vm.isEmailVerified) {
                LaunchedEffect(key1 = Unit) {
                    navigateToHomeScreen()
                }
            }
            else{
                showErrorMessage("Email not authorised")
            }
        }
        is Response.Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                showErrorMessage(e.message)
            }
        }
    }
}