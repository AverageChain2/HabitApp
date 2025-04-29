package com.example.habitapp.presentation.screens.editHabitScreen

import HabitRepo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.repository.AuthRepo

class HabitProgressScreenViewModel (private val authRepo: AuthRepo,
                     private val repo: HabitRepo) : ViewModel() {
    private var selectedHabit : Habit? = null
    private var id by mutableStateOf(String())
    private var date by mutableStateOf(String())

    var unit by mutableStateOf(String())
    fun unitIsValid(): Boolean {
        return unit.isNotBlank()
    }

    var goal by mutableStateOf(String())
    fun goalIsValid(): Boolean {
        return goal.isNotBlank() && goal.toIntOrNull()?.let { it > 0 } ?: false
    }

    var timeframe by mutableStateOf(String())
    fun timeframeIsValid(): Boolean {
        return timeframe.isNotBlank() && timeframe.toIntOrNull()?.let { it > 0 } ?: false    }

    var group by mutableStateOf(String())
    fun groupIsValid(): Boolean {
        return group.isNotBlank()
    }

    var progress by mutableStateOf(String())
    fun progressIsValid(): Boolean {
        return progress.isNotBlank()
    }

    fun setSelectedHabit(habit: Habit){
    id = habit.id.toString()
    unit = unit
    goal = goal
    progress = progress
    timeframe = timeframe
    selectedHabit = habit
    }

    fun updateHabit(){
        if (unitIsValid() && goalIsValid() && timeframeIsValid() && groupIsValid() && progressIsValid()) {

            selectedHabit!!.unit = unit
            selectedHabit!!.goal = goal.toInt()
            selectedHabit!!.progress = progress.toInt()
            selectedHabit!!.timeframe = timeframe.toInt()
    repo.edit(
        selectedHabit!!, authRepo.currentUser!!.uid,
        group, date
    )
    }
    }
    }