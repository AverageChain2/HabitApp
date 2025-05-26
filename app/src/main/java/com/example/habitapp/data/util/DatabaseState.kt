package com.example.habitapp.data.util

data class DatabaseState<T>(
    val data: List<T?> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String=String()
)

data class DatabaseStateSingle<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val errorMessage: String=String()
)