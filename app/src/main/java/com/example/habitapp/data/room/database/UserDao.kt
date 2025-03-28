package com.example.habitapp.data.room.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getUsersOrderByFirstName(): Flow<List<User>>
}