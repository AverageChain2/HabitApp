package com.example.habitapp.data.room.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user")

data class User(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val email: String,
)
