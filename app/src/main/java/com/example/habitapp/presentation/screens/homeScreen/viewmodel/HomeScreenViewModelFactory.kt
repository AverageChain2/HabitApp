package com.example.habitapp.presentation.screens.homeScreen.viewmodel

import HomeScreenViewmodel
import Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.presentation.screens.addHabitScreen.AddHabitScreen
import com.example.habitapp.presentation.screens.addHabitScreen.AddHabitViewModel

object HomeScreenViewModelFactory {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer { HomeScreenViewmodel(repo = HabitApplication.getRepository()) }
        initializer { AddHabitViewModel(repo = HabitApplication.getRepository()) }
//        initializer { EditViewModel(repo = HabitApplication.getRepository()) }
    }
}