//package com.example.habitapp.presentation.components
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.windowInsetsPadding
//import androidx.compose.foundation.text.KeyboardOptions
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun RegisterForm(
//    state: HabitState,
//    onEvent: (HabitEvent) -> Unit,
//    modifier: Modifier
//
//) { Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
//    TextField(
//        value = state.unit,
//        onValueChange = {
//            onEvent(HabitEvent.SetUnit(it))
//        },
//        placeholder = {
//            Text(text = "Unit")
//        }
//    )
//    TextField(
//        value = state.goal,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Number
//        ),
//        onValueChange = {
//            onEvent(HabitEvent.SetGoal(it))
//        },
//        placeholder = {
//            Text(text = "Goal")
//        }
//    )
//    TextField(
//        value = state.timeframe,
//        onValueChange = {
//            onEvent(HabitEvent.SetTimeframe(it))
//        },
//        placeholder = {
//            Text(text = "Timeframe")
//        }
//    )
//    TextField(
//        value = state.group,
//        onValueChange = {
//            onEvent(HabitEvent.SetGroup(it))
//        },
//        placeholder = {
//            Text(text = "Group")
//        }
//    )
//    Button(onClick = {
//        onEvent(HabitEvent.SaveHabit)
//    }) {
//        Text(text = "Save")
//    }
//
//}
//
//}