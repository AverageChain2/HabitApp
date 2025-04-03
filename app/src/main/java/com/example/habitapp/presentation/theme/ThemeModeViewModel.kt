package com.example.habitapp.presentation.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThemeModeViewModel : ViewModel() {
    private val _isDarkTheme = mutableStateOf(true)
    val isDarkTheme: State<Boolean> get() = _isDarkTheme

    fun onModeChange() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}

