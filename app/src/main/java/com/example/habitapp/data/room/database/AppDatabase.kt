package com.example.habitapp.data.room.database

import LocalDateConverter
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.data.room.database.habit.HabitDao


@Database(entities = [Habit::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "habit_database"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}