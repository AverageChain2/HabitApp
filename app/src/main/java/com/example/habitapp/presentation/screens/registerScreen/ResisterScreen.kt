package com.example.habitapp.presentation.screens.registerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habitapp.R
import com.example.habitapp.ViewModelFactory
import com.example.habitapp.presentation.components.CustomButton
import com.example.habitapp.presentation.components.SmallSpacer
import com.example.habitapp.presentation.components.CustomTextField




@Composable
fun RegisterScreen(vm: RegisterViewModel = viewModel(factory =
ViewModelFactory.Factory),
                   navigateBack: () -> Unit) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    Scaffold(
        content = { padding ->
            Text(text = "Enter details to sign up")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    stringResource(R.string.email),
                    text = vm.email,
                    isPasswordField = false,
                    onValueChange = { vm.email = it },
                    stringResource(R.string.email_error_message),
                    errorPresent = vm.emailIsValid()
                )
                SmallSpacer()
                CustomTextField(
                    stringResource(R.string.password),
                    text = vm.password,
                    isPasswordField = true,
                    onValueChange = { vm.password = it },
                    stringResource(R.string.password_error_message),
                    errorPresent = vm.passwordIsValid()
                )
                SmallSpacer()
                CustomButton(
                    stringResource(R.string.submit_button),
                    clickButton = {
                        keyboard?.hide()
                        vm.signUpWithEmailAndPassword()
                    }
                )
                Row {
                    CustomButton(
                        stringResource(R.string.back_button),
                        clickButton = {
                            navigateBack()
                        }
                    )
                }
            }
        }
    )
}