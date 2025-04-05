package com.example.habitapp.data.room

import com.example.habitapp.data.room.database.user.User

data class UserState(
    val users: List<User> = emptyList(),
    val firstName: String = "",
    val surname: String = "",
    val telNo: String = "",
    val isAddingUser: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)
