package com.example.habitapp.data.room.database.habit

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface HabitDao {

    @Upsert
    suspend fun insertHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT * FROM habit")
    fun getHabits(): Flow<List<Habit>>

    @Query("SELECT * FROM Habit WHERE userId = :userId")
    suspend fun getHabitsForUser(userId: UUID): List<Habit>

}