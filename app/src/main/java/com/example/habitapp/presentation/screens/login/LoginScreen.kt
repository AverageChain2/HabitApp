package com.example.habitapp.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habitapp.R
import com.example.habitapp.ViewModelFactory
import com.example.habitapp.presentation.components.CustomButton
import com.example.habitapp.presentation.components.CustomTextField
import com.example.habitapp.presentation.components.SmallSpacer
import com.example.habitapp.presentation.screens.login.components.LogIn
import com.example.habitapp.util.Util.Companion.showMessage


@Composable
fun LoginScreen(vm: LoginViewModel = viewModel(factory = ViewModelFactory.Factory),

                navigateToHomeScreen: () -> Unit) {
    val context = LocalContext.current
    val message: String by vm.message.observeAsState(String())

    if (message.length>0){ //Only changes when vm message is updated
        showMessage(context, vm.message.value)
    }

    Scaffold { padding ->
        val keyboard = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                hintText = stringResource(R.string.email),
                text = vm.email,
                isPasswordField = false,
                onValueChange = { vm.email = it },
                stringResource(R.string.email_error_message),
                vm.emailIsValid()
            )
            SmallSpacer()
            CustomTextField(
                hintText = stringResource(R.string.password),
                text = vm.password,
                isPasswordField = true,
                onValueChange = { vm.password = it },
                stringResource(R.string.password_error_message),
                vm.passwordIsValid()
            )
            SmallSpacer()
            CustomButton(
                stringResource(R.string.submit_button),
                clickButton = {
                    keyboard?.hide()
                    vm.signInWithEmailAndPassword()
                }
            )
            SmallSpacer()
            CustomButton(
                stringResource(R.string.forgot_password),
                clickButton = {
                    if (vm.emailIsValid()) {
                        vm.forgotPassword()
                    } else {
                        showMessage(context, "valid email to retrieve password")
                    }
                }
            )
            SmallSpacer()

        }
    }

    LogIn(vm=vm,
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        },
        navigateToHomeScreen = navigateToHomeScreen
    )
}