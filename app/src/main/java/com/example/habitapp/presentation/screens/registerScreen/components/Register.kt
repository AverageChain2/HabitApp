package com.example.habitapp.presentation.screens.registerScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.habitapp.data.model.Response
import com.example.habitapp.presentation.components.ProgressBar
import com.example.habitapp.presentation.screens.registerScreen.RegisterViewModel

@Composable
fun Register(vm: RegisterViewModel,
           sendEmailVerification: () -> Unit,
           showVerifyEmailMessage: () -> Unit,
           showFailureToSignUpMessage: () -> Unit
)  {
    when(val signUpResponse = vm.signUpResponse) {
        is Response.Loading, Response.Startup -> ProgressBar()
        is Response.Success -> {
            val isUserSignedUp = signUpResponse.data
            LaunchedEffect(isUserSignedUp) {
                if (isUserSignedUp) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                }
            }
        }
        is Response.Failure -> signUpResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showFailureToSignUpMessage()
            }
        }
    }
}
