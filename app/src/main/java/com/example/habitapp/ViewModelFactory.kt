package com.example.habitapp

import com.example.habitapp.presentation.screens.homeScreen.viewmodel.HomeScreenViewmodel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.habitapp.presentation.screens.addHabitScreen.AddHabitViewModel
import com.example.habitapp.presentation.screens.editHabitScreen.viewmodel.EditViewModel
import com.example.habitapp.presentation.screens.habitProgressScreen.viewmodel.HabitProgressScreenViewModel
import com.example.habitapp.presentation.screens.login.viewmodel.LoginViewModel
import com.example.habitapp.presentation.screens.registerScreen.viewmodel.RegisterViewModel

object ViewModelFactory {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            AddHabitViewModel(
                authRepo = HabitApplication.getAuthRepository(),
                habitRepo = HabitApplication.getHabitRepository()
            )
        }
        initializer {
            EditViewModel(
                authRepo = HabitApplication.getAuthRepository(),
                repo = HabitApplication.getHabitRepository()
            )
        }
        initializer {
            HomeScreenViewmodel(
                authRepo = HabitApplication.getAuthRepository(),
                habitRepo = HabitApplication.getHabitRepository(),
            )
        }
        initializer {
            HabitProgressScreenViewModel(
                authRepo = HabitApplication.getAuthRepository(),
                habitRepo = HabitApplication.getHabitRepository(),
            )
        }
        initializer { LoginViewModel(repo = HabitApplication.getAuthRepository()) }
        initializer { RegisterViewModel (repo = HabitApplication.getAuthRepository()) }
    }
}