package com.example.habitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habitapp.presentation.navigation.NavigationGraph
import com.example.habitapp.presentation.theme.AppTheme
import com.example.habitapp.presentation.theme.ThemeModeViewModel

class MainActivity : ComponentActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

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



