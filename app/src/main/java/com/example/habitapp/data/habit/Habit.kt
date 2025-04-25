package com.example.habitapp.data.habit

data class Habit(
    val group: String = "",
    val unit: String = "",
    val goal: Int = 1,
    val timeframe: Int = 1,
    val progress: Int = 0,
    val timestamp: String = ""
) {
    var id:String? =null
}
