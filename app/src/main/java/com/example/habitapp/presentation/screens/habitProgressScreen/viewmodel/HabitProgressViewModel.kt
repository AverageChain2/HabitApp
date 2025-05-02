package com.example.habitapp.presentation.screens.habitProgressScreen.viewmodel

import HabitRepo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.repository.AuthRepo
import com.example.habitapp.data.util.DatabaseResult
import com.example.habitapp.data.util.DatabaseStateSingle

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HabitProgressScreenViewModel(
    private val authRepo: AuthRepo,
    private val habitRepo: HabitRepo
) : ViewModel() {

    private val _selectedHabit = MutableStateFlow(DatabaseStateSingle<Habit>())
    val selectedHabit: StateFlow<DatabaseStateSingle<Habit>> = _selectedHabit.asStateFlow()


    var progress by mutableStateOf(String())
    fun progressIsValid(): Boolean {
        return progress.isNotBlank()
    }

    fun calculateProgress(): Float {
        return _selectedHabit.value.data?.let {
            it.progress.toFloat() / it.goal.toFloat()
        } ?: 0f
    }


    fun getSingleHabit(habit: Habit) {
        val userId = authRepo.currentUser?.uid ?: return
        viewModelScope.launch {
            habitRepo.getSingleHabit(userId, habit).collect { result ->
                when (result) {
                    is DatabaseResult.Loading -> _selectedHabit.update { it.copy(isLoading = true) }
                    is DatabaseResult.Success -> {
                        _selectedHabit.update {
                            it.copy(
                                data = result.data,
                                isLoading = false,

                                )


                        }
                        progress = result.data?.progress.toString()
                    }

                    is DatabaseResult.Error -> _selectedHabit.update {
                        it.copy(
                            errorMessage = result.exception.message ?: "Unknown error"
                        )
                    }
                }
            }
        }
    }


    fun updateHabit() {
        _selectedHabit.value.data?.let { habit ->
            progress.toIntOrNull()?.let { newProgress ->
                if (newProgress >= 0 && newProgress <= _selectedHabit.value.data!!.goal ) {
                    val updatedHabit =
                        habit.copy(progress = newProgress)
                    updatedHabit.date = _selectedHabit.value.data!!.date
                    updatedHabit.group = _selectedHabit.value.data!!.group
                    updatedHabit.id = _selectedHabit.value.data!!.id

                    habitRepo.edit(
                        updatedHabit,
                        authRepo.currentUser?.uid ?: return
                    )
                }
            }
        }
    }
}