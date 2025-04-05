package com.example.habitapp.data.room.database.group

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "groups")

data class Group(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val groupName: String
)
