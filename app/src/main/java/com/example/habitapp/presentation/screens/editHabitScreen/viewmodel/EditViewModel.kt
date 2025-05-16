package com.example.habitapp.presentation.screens.editHabitScreen.viewmodel

import HabitRepo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.repository.AuthRepo
import kotlinx.coroutines.launch

class EditViewModel (private val authRepo: AuthRepo,
                     private val repo: HabitRepo) : ViewModel() {

    private var selectedHabit : Habit? = null
    private var id by mutableStateOf(String())

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
        unit = habit.unit
        goal = habit.goal.toString()
        progress = habit.progress.toString()
        timeframe = habit.timeframe.toString()
        group = habit.group.toString()
        selectedHabit = habit
    }


    fun updateHabit(){
        if (unitIsValid() && goalIsValid() && timeframeIsValid() && groupIsValid()) {
            val updatedHabit =
                selectedHabit?.copy()
            if (updatedHabit != null) {

                updatedHabit.unit = unit

            updatedHabit.goal = goal.toInt()
            updatedHabit.progress = progress.toInt()
            updatedHabit.timeframe = timeframe.toInt()
            updatedHabit.date = selectedHabit?.date

            updatedHabit.group = selectedHabit?.group
            updatedHabit.id = selectedHabit?.id

                if (group != selectedHabit?.group) {
                    viewModelScope.launch {

                            repo.batchUpdateGroup(updatedHabit, authRepo.currentUser!!.uid,
                                group
                            )

                    }
                }
            }

    repo.edit(
        updatedHabit!!, authRepo.currentUser!!.uid

    )
    }
    }


    }