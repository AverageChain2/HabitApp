package com.example.habitapp.presentation.screens.registerScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.habitapp.data.model.Response
import com.example.habitapp.presentation.components.ProgressBar
import com.example.habitapp.presentation.screens.registerScreen.viewmodel.RegisterViewModel

@Composable
fun Register(vm: RegisterViewModel,
           sendEmailVerification: () -> Unit,
           showVerifyEmailMessage: () -> Unit,
           showFailureToRegisterMessage: () -> Unit,
             navigateToLogin: () -> Unit
)  {
    when(val registerResponse = vm.registerResponse) {
        is Response.Loading, Response.Startup -> ProgressBar()
        is Response.Success -> {
            val isUserRegistered = registerResponse.data
            LaunchedEffect(isUserRegistered) {
                if (isUserRegistered) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                    navigateToLogin()
                }
            }
        }
        is Response.Failure -> registerResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showFailureToRegisterMessage()
            }
        }
    }
}
