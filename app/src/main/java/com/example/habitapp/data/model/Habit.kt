package com.example.habitapp.data.model

data class Habit (
    var unit: String ? =null,
    var goal: Number ? =null,
    var progress:Number ? =null,
    var timeframe:Number ? = null,
    var groupId: Number ? = null
) {
//    override fun toString(): String = "$firstName $surname $telNo"
}
