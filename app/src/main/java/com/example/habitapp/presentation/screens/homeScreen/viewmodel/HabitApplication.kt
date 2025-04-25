package com.example.habitapp.presentation.screens.homeScreen.viewmodel

import HabitRepository
import Repository
import android.app.Application
import com.example.habitapp.data.room.database.AppDatabase
import com.example.habitapp.data.room.database.habit.Habit

class HabitApplication : Application() {
    companion object {
        private lateinit var habitRepository: Repository<Habit>
        fun getRepository(): Repository<Habit> = habitRepository
    }
    override fun onCreate() {
        super.onCreate()
        habitRepository =
            HabitRepository(AppDatabase.getDatabase(this).habitDao())
    }
}