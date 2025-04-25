package com.example.habitapp.data.room.database.habit

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "habit"
)

data class Habit(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val unit: String = "",
    val goal: Int = 1,
    var progress:Int = 0,
    var timeframe: Int =1,
    var group: String,
    var timeStamp: LocalDate
)
