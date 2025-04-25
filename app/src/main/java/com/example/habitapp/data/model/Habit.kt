package com.example.habitapp.data.model

import java.time.LocalDate
import java.util.UUID

data class Habit (
    var id: UUID,
    var unit: String ? ="",
    var goal: Number ? =1,
    var progress:Number ? =0,
    var timeframe:Number ? = 1,
    var group: String ? = "",
    var timeStamp: LocalDate
) {
//    override fun toString(): String = "$firstName $surname $telNo"
}
