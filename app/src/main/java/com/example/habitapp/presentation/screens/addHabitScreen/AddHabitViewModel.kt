package com.example.habitapp.presentation.screens.addHabitScreen

import Repository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.habitapp.data.room.database.habit.Habit
import java.time.LocalDate
import java.util.UUID

class AddHabitViewModel(private val repo: Repository<Habit>) : ViewModel() {

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

    suspend fun addHabit() {
        if (unitIsValid() && goalIsValid() && timeframeIsValid() && groupIsValid()) {
            val newHabit = Habit(
                id = UUID.randomUUID(),
                unit = unit,
                goal = goal.toInt(),
                progress = progress,
                timeframe = timeframe.toInt(),
                group = group,
                timeStamp = LocalDate.now()
            )
            repo.insert(newHabit)
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