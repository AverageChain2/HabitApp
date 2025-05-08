package com.example.habitapp.util.dailyCopy

import DailyWorker
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import scheduleDailyTask

class MyAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("MyAlarmReceiver", "Alarm triggered!")
        // You can start your worker/task here
        val workRequest = OneTimeWorkRequestBuilder<DailyWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)

        scheduleDailyTask(context)


    }
}