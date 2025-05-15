package com.example.habitapp.presentation.screens.registerScreen.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.model.Response
import com.example.habitapp.data.repository.AuthRepo
import kotlinx.coroutines.launch

class RegisterViewModel (private val repo: AuthRepo) : ViewModel() {
    var email by mutableStateOf(String())

    fun emailIsValid():Boolean{
        return email.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    var password by mutableStateOf(String())
    fun passwordIsValid():Boolean{
        return password.length >=6 //6 minimum
    }

    var registerResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    private var sendEmailVerificationResponse by mutableStateOf<Response<Boolean>>(
        Response.Success(
            false
        )
    )

    fun registerWithEmailAndPassword() = viewModelScope.launch {
        registerResponse = Response.Loading
        registerResponse = repo.firebaseRegisterWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Response.Loading
        sendEmailVerificationResponse = repo.sendEmailVerification()
    }
}