package com.example.habitapp.presentation.screens.loginScreen.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.model.Response
import com.example.habitapp.data.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: AuthRepo) : ViewModel() {
    var email by mutableStateOf(String())
    fun emailIsValid():Boolean{
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    var password by mutableStateOf(String())
    fun passwordIsValid():Boolean{
        return password.length >=6 //6 minimum
    }

    private var _message = MutableLiveData(String())
    var message: LiveData<String> = _message

    val isEmailVerified get() = repo.currentUser?.isEmailVerified == true

    var signInResponse by mutableStateOf<Response<Boolean>>(Response.Startup)
        private set

    fun forgotPassword() {
        FirebaseAuth.getInstance()
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                _message.value = if (task.isSuccessful) {
                    "Password reset email has been sent successfully"
                } else {
                    task.exception?.localizedMessage ?: "Unable to send password reset email"
                }
            }
    }

    fun signInWithEmailAndPassword() = viewModelScope.launch {
        try {
            signInResponse = Response.Loading
            signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
        } catch (e: Exception) {
            signInResponse = Response.Failure(e)
        }
    }
}