package com.example.habitapp.data.room.database.habit

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.habitapp.data.room.database.group.Group
import com.example.habitapp.data.room.database.user.User
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "habit",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Group::class,
        parentColumns = ["id"],
        childColumns = ["groupId"],
        onDelete = ForeignKey.CASCADE
    )
                  ],
    indices = [Index("userId"), Index("groupId")]
)

data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val unit: String,
    val goal: Int,
    var progress:Int ? =0,
    var timeframe:Int ? =1,
    var userId: UUID,
    var groupId: Int,
    var timeStamp: LocalDate
)
