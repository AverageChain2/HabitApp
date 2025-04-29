package com.example.habitapp.presentation.screens.addHabitScreen


import HabitRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.repository.AuthRepo
import java.time.LocalDate
import java.util.UUID

class AddHabitViewModel(private val authRepo: AuthRepo, private val habitRepo: HabitRepository) : ViewModel() {

    var unit by mutableStateOf("")
    fun unitIsValid(): Boolean {
        return unit.isNotBlank()
    }

    var goal by mutableStateOf("")
    fun goalIsValid(): Boolean {
        return goal.isNotBlank() && goal.toIntOrNull()?.let { it > 0 } ?: false
    }

    var timeframe by mutableStateOf("")
    fun timeframeIsValid(): Boolean {
        return timeframe.isNotBlank() && timeframe.toIntOrNull()?.let { it > 0 } ?: false    }

    var group by mutableStateOf("")
    fun groupIsValid(): Boolean {
        return group.isNotBlank()
    }

    var progress by mutableStateOf(0)

    fun addHabit() {
        if (unitIsValid() && goalIsValid() && timeframeIsValid() && groupIsValid()) {
            val newHabit = Habit(
                unit = unit,
                goal = goal.toInt(),
                progress = progress,
                timeframe = timeframe.toInt(),
            )
            habitRepo.add(newHabit, group, authRepo.currentUser!!.uid)
            clear()
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