package com.example.habitapp

import HabitRepository
import android.app.Application
import com.example.habitapp.data.habit.HabitDAO
import com.example.habitapp.data.repository.AuthRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val HABIT_ROOT_FOLDER = "habits"
private const val DATABASE_URL = "https://habitapp-4a07f-default-rtdb.europe-west1.firebasedatabase.app/"
class HabitApplication : Application() {
    companion object {
        private lateinit var authRepository: AuthRepository
        private lateinit var habitRepository: HabitRepository
        lateinit var habitRoot: DatabaseReference
        fun getAuthRepository(): AuthRepository = authRepository
        fun getHabitRepository(): HabitRepository = habitRepository
    }
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        authRepository = AuthRepository(FirebaseAuth.getInstance())
        habitRoot =
            FirebaseDatabase.getInstance(DATABASE_URL).getReference(HABIT_ROOT_FOLDER)
        habitRepository = HabitRepository(HabitDAO(habitRoot))
    }
}