//package com.example.habitapp.presentation.screens.addScreen
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.habitapp.R
//import com.example.habitapp.presentation.screens.OverallDisplay
//import com.example.habitapp.presentation.screens.addHabitScreen.AddHabitScreen
//
//@Composable
//fun AddScreen(navController: NavController,
//              ) {
//    OverallDisplay (navController = navController, content = { modifier ->
//        Column(
//            modifier = modifier
//        ) {
//            Text(
//                modifier = Modifier.align(Alignment.CenterHorizontally),
//                text = stringResource(R.string.add),
//                textAlign = TextAlign.Center,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//            )
//            AddHabitScreen("Add2", habitDao = )
//        }
//    })
//}