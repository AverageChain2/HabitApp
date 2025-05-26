package com.example.habitapp.presentation.screens.addHabitScreen

import HabitRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.repository.AuthRepo

class AddHabitViewModel(private val authRepo: AuthRepo, private val habitRepo: HabitRepository) :
    ViewModel() {

    var unit by mutableStateOf("")
    fun unitIsValid(): Boolean {
        return unit.isNotBlank() && unit.length <= 50
    }

    var goal by mutableStateOf("")
    fun goalIsValid(): Boolean {
        val goalInt = goal.toIntOrNull()
        return goalInt != null && goalInt > 0
    }

    var timeframe by mutableStateOf("")
    fun timeframeIsValid(): Boolean {
        val timeframeInt = timeframe.toIntOrNull()
        return timeframeInt != null && timeframeInt > 0
    }

    var group by mutableStateOf("")
    fun groupIsValid(): Boolean {
        return group.isNotBlank() && group.length <= 50
    }

    var progress by mutableStateOf(0)

    fun addHabit(): Boolean {
        if (unitIsValid() && goalIsValid() && timeframeIsValid() && groupIsValid()) {
            val currentUserId = authRepo.currentUser?.uid
            if (currentUserId == null) {
                println("Error: User is not authenticated. Cannot add habit.")
                return false
            }

            val newHabit = Habit(
                unit = unit,
                goal = goal.toInt(),
                progress = progress,
                timeframe = timeframe.toInt(),
            )
            habitRepo.add(newHabit, group, currentUserId)
            clear()
            return true
        } else {
            println("Validation failed. Habit not added.")
            return false
        }
    }

    private fun clear() {
        unit = ""
        goal = ""
        timeframe = ""
        group = ""
        progress = 0
    }
}