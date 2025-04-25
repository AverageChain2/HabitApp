package com.example.habitapp.data.room

import com.example.habitapp.data.room.database.habit.Habit
import java.time.LocalDate
import java.util.UUID

data class HabitState(
    val habits: List<Habit> = emptyList(),
//    val id: UUID,
    val unit: String = "",
    val goal: String ="0",
    var timeframe:String ="0",
    var group: String = "",
//    var timeStamp: LocalDate = LocalDate.now(),
    val isAddingHabit: Boolean = false,
//    val sortType: SortType = SortType.FIRST_NAME
)
