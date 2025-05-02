package com.example.habitapp.presentation.screens.homeScreen.components


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.habitapp.data.habit.Habit

//import com.example.habitapp.data.room.database.habit.Habit

@Composable
fun HabitCard(
    modifier: Modifier = Modifier,
    habit: Habit,
    group: String,
    selectHabit: (Habit) -> Unit,
//    vm: com.example.habitapp.presentation.screens.homeScreen.viewmodel.HomeScreenViewmodel,
    habitProgressButton: (Habit) -> Unit
) {
    ElevatedCard(
        onClick = {
            selectHabit(habit) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Goal: ${habit.goal} ${habit.unit}s",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Progress: ${habit.progress}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Group: $group",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))

            Log.d("HabitCard", "$habit ${habit.id}")

            if (habit.progress == habit.goal) {

                    IconButton(onClick = { habitProgressButton(habit) },
                        modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Localized description",
                        )
                    }
            } else {
                IconButton(onClick = { habitProgressButton(habit) },
                    modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Localized description",
                    )
                }
            }


        }
    }
}