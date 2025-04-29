package com.example.habitapp.data.habit

data class Habit(
    var unit: String = "",
    var goal: Int = 1,
    var timeframe: Int = 1,
    var progress: Int = 0,
) {
    var id:String? =null
    var group:String? = null
    var date:String? = null
}
