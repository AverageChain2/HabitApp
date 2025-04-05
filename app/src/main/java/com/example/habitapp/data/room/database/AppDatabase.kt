package com.example.habitapp.data.room.database

import LocalDateConverter
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habitapp.data.room.database.user.User
import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.data.room.database.group.Group
import com.example.habitapp.data.room.database.group.GroupDao
import com.example.habitapp.data.room.database.habit.HabitDao
import com.example.habitapp.data.room.database.user.UserDao


@Database(entities = [User::class, Habit::class, Group::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao
    abstract fun groupDao(): GroupDao
}