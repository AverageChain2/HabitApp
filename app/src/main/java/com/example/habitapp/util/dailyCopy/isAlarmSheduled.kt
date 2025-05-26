package com.example.habitapp.util.dailyCopy

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

fun isAlarmScheduled(context: Context): Boolean {
    val intent = Intent(context, MyAlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
    )
    return pendingIntent != null
}
