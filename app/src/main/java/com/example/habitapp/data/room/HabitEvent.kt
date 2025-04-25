package com.example.habitapp.data.room

import com.example.habitapp.data.room.database.habit.Habit

sealed interface HabitEvent {

    object SaveHabit: HabitEvent
    data class SetUnit(val unit: String): HabitEvent
    data class SetGoal(val goal: String): HabitEvent
    data class SetTimeframe(val timeframe: String): HabitEvent
    data class SetGroup(val group: String): HabitEvent
    object ShowDialog: HabitEvent
    object HideDialog: HabitEvent
    data class SortHabit(val sortType: SortType): HabitEvent
    data class DeleteHabit(val habit: Habit): HabitEvent

}