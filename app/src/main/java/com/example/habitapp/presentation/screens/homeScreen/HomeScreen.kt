package com.example.habitapp.presentation.screens.homeScreen


import HomeScreenViewmodel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habitapp.ViewModelFactory
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.util.DatabaseResult
//import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.presentation.components.DateSelector
import com.example.habitapp.presentation.components.DateSelectorViewModel
import com.example.habitapp.presentation.components.GroupSelect
import com.example.habitapp.presentation.components.ProgressBar
import com.example.habitapp.presentation.components.ProgressIndicator
import com.example.habitapp.presentation.screens.OverallDisplay
import com.example.habitapp.presentation.screens.homeScreen.components.HabitCard
import com.example.habitapp.util.Util.Companion.showMessage

//import com.example.habitapp.presentation.screens.homeScreen.viewmodel.HomeScreenViewModelFactory

@Composable
fun HomeScreen(
    text: String,
    navController: NavController,
    dateSelectorViewModel: DateSelectorViewModel = viewModel(),
    homeScreenViewModel: HomeScreenViewmodel = viewModel(factory = ViewModelFactory.Factory)

)
{
    val selectedDate by dateSelectorViewModel.selectedDate.observeAsState()
    val userState by homeScreenViewModel.userState.collectAsState()


    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    OverallDisplay (navController = navController, content = { modifier ->

    Column(
            modifier = modifier.padding()
        ) {
            selectedDate?.let {
                DateSelector(navController, Modifier, it, onDateChange = { newDate ->
                    dateSelectorViewModel.onDateChange(newDate)})
            }
            selectedDate?.let { ProgressIndicator(modifier, it) }

            GroupSelect()

        when {
            userState.isLoading -> {
                ProgressBar()
            }

            userState.data.isNotEmpty() -> {
                Column {
                    userState.data.forEach { habit ->
                        if (habit != null) {
                            HabitCard(modifier = Modifier, habit = habit)
                        }
                    }
                }
            }

            userState.errorMessage.isNotBlank() -> {
                showMessage(context, userState.errorMessage)
            }
        }




        }
    })
}


