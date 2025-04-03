package com.example.habitapp

import android.app.Application
import android.util.Log
import com.example.habitapp.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class ContactApplication : Application()  {
    companion object {
        private var authRepository: AuthRepository = AuthRepository(FirebaseAuth.getInstance())
        fun getAuthRepository(): AuthRepository = authRepository
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("ContactApplication", "Application initialized successfully")
    }
}