package com.example.habitapp

import MainViewModel
import MainViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.habitapp.data.room.database.AppDatabase
import com.example.habitapp.data.room.database.group.Group
import com.example.habitapp.data.room.database.group.GroupDao
import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.data.room.database.habit.HabitDao
import com.example.habitapp.data.room.database.user.User
import com.example.habitapp.data.room.database.user.UserDao
import com.example.habitapp.presentation.navigation.NavigationGraph
import com.example.habitapp.presentation.theme.AppTheme
import com.example.habitapp.presentation.theme.ThemeModeViewModel
import java.time.LocalDate
import java.util.UUID

class MainActivity : ComponentActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                applicationContext.deleteDatabase("app-database")

                // Initialize Room database
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "app-database"
                ).build()

                // Set up ViewModel
                val viewModel = ViewModelProvider(this, MainViewModelFactory(db))
                    .get(MainViewModel::class.java)

                // Perform database operations
                viewModel.performDatabaseOperations()





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



