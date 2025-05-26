package com.example.habitapp

import HabitRepository
import android.app.Application
import com.example.habitapp.data.habit.HabitDAO
import com.example.habitapp.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val HABIT_ROOT_FOLDER = "habits"
private const val TEST_HABIT_ROOT_FOLDER = "test_habits"
private const val DATABASE_URL = "https://habitapp-4a07f-default-rtdb.europe-west1.firebasedatabase.app/"

class HabitApplication : Application() {

    companion object {
        private lateinit var authRepository: AuthRepository
        private lateinit var habitRepository: HabitRepository
        lateinit var habitRoot: DatabaseReference

        fun getAuthRepository(): AuthRepository = authRepository
        fun getHabitRepository(): HabitRepository = habitRepository

        // Check if the app is running in a test environment
        val isRunningTest: Boolean by lazy {
            try {
                Class.forName("androidx.test.espresso.Espresso")
                true
            } catch (e: ClassNotFoundException) {
                false
            }
        }
    }



    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance(DATABASE_URL).setPersistenceEnabled(true)
        authRepository = AuthRepository(FirebaseAuth.getInstance())

        // Use separate paths for test vs. production
        val habitPath = if (isRunningTest) TEST_HABIT_ROOT_FOLDER else HABIT_ROOT_FOLDER
        habitRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(habitPath)

        habitRepository = HabitRepository(HabitDAO(habitRoot))
    }
}