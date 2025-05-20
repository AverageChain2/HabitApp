package com.example.habitapp

import android.app.AlarmManager
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.habitapp.presentation.navigation.NavigationGraph
import com.example.habitapp.presentation.theme.AppTheme
import com.example.habitapp.presentation.theme.ThemeModeViewModel
import com.example.habitapp.util.dailyCopy.isAlarmScheduled
import com.google.firebase.Firebase
import com.google.firebase.database.database
import scheduleDailyTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        val alarmManager = getSystemService(AlarmManager::class.java)
        if (!alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            startActivity(intent)
        }

        if (!isAlarmScheduled(this)) {
            Log.e("MainActivity", "Alarm is not scheduled")
            scheduleDailyTask(this)
        } else {
            Log.e("MainActivity", "Alarm is scheduled")
        }


        enableEdgeToEdge()

        val themeModeViewModel = ViewModelProvider(this).get(ThemeModeViewModel::class.java)

        setContent {
            AppTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }

}



