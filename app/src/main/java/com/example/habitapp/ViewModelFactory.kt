package com.example.habitapp

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.habitapp.presentation.screens.login.LoginViewModel
import com.example.habitapp.presentation.screens.registerScreen.RegisterViewModel

object ViewModelFactory {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer { LoginViewModel(repo = ContactApplication.getAuthRepository()) }
        initializer { RegisterViewModel (repo = ContactApplication.getAuthRepository()) }
    }
}