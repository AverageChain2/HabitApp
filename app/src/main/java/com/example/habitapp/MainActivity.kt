package com.example.habitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.habitapp.data.model.User
import com.example.habitapp.ui.theme.HabitAppTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID
import com.example.habitapp.data.repository.UserRepository
import com.example.habitapp.presentation.viewModel.UserScreen
import com.example.habitapp.presentation.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = UserViewModel() // Initialize ViewModel
        viewModel.fetchUserRecord("2f9cdb56-7400-496b-82d9-727d378f8846") // Fetch user data

        enableEdgeToEdge()
        setContent {
            HabitAppTheme {
                Scaffold(
                    content = { padding ->
                    UserScreen(viewModel, modifier = Modifier.padding(padding).fillMaxHeight().fillMaxWidth()) // Pass ViewModel to Composable
                })
                }
            }
        }

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabitAppTheme {
        Greeting("Android")
    }
}