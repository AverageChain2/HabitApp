//package com.example.habitapp.presentation.screens.addHabitScreen
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.habitapp.data.room.SortType
//import com.example.habitapp.data.room.HabitEvent
//import com.example.habitapp.data.room.HabitState
//import com.example.habitapp.data.room.database.habit.Habit
//import com.example.habitapp.data.room.database.habit.HabitDao
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import java.time.LocalDate
//import java.util.UUID
//
//class HabitViewModel(private val dao: HabitDao) : ViewModel() {
//
//    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
//    private val _habits = dao.getHabits()
//    private val _state = MutableStateFlow(HabitState())
//
//    val state = combine(_state, _habits) {
//        state, habits ->
//        state.copy(
//            habits = habits
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HabitState())
//
//    fun onEvent(event: HabitEvent) {
//        when (event) {
//            is HabitEvent.DeleteHabit -> {
//                viewModelScope.launch {
//                    dao.deleteHabit(event.habit)
//                }
//            }
//            HabitEvent.HideDialog -> {
//                _state.update { it.copy(
//                    isAddingHabit = false
//                ) }
//            }
//            HabitEvent.SaveHabit -> {
//                val unit = state.value.unit
//                val goal = state.value.goal
//                val timeframe = state.value.timeframe
//                val group = state.value.group
//
//
//                if(unit.isBlank() || group.isBlank()) {
//                    return
//                }
//                val habit = Habit(
//                    unit = unit,
//                    goal = goal.toInt(),
//                    progress = 0,
//                    timeframe = timeframe.toInt(),
//                    group = group,
//                    timeStamp = LocalDate.now(),
//                    id = UUID.randomUUID(),
//
//
//                )
//                viewModelScope.launch {
//                    dao.insertHabit(habit)
//                }
//                _state.update {
//                    it.copy(
//                        isAddingHabit = false,
//                        unit = "",
//                        goal = "0",
//                        timeframe = "0",
//                        group = ""
//                    )
//                }
//            }
//            is HabitEvent.SetUnit -> {
//                _state.update { it.copy(
//                    unit = event.unit
//                ) }
//            }
//            is HabitEvent.SetGoal -> {
//                _state.update { it.copy(
//                    goal = event.goal
//                ) }
//            }
//
//            is HabitEvent.SetTimeframe -> {
//                _state.update { it.copy(
//                    timeframe = event.timeframe
//                ) }
//            }
//            is HabitEvent.SetGroup -> {
//                _state.update { it.copy(
//                    group = event.group
//                ) }
//            }
//            HabitEvent.ShowDialog -> {
//                _state.update { it.copy(
//                    isAddingHabit = true
//                ) }
//            }
//            is HabitEvent.SortHabit -> {
//                _sortType.value = event.sortType
//            }
//        }
//    }
//}