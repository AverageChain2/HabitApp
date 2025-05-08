package com.example.habitapp.presentation.screens.homeScreen


import com.example.habitapp.presentation.screens.homeScreen.viewmodel.HomeScreenViewmodel
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habitapp.ViewModelFactory
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.presentation.components.DateSelector
import com.example.habitapp.presentation.components.GroupSelect
import com.example.habitapp.presentation.components.ProgressBar
import com.example.habitapp.presentation.components.ProgressIndicator
import com.example.habitapp.presentation.screens.StandardLayout
import com.example.habitapp.presentation.screens.homeScreen.components.HabitCard
import com.example.habitapp.util.Util.Companion.showMessage
import com.google.firebase.auth.FirebaseAuth

//import com.example.habitapp.presentation.screens.homeScreen.viewmodel.HomeScreenViewModelFactory

@Composable
fun HomeScreen(
    text: String,
    selectHabit: (Habit) -> Unit,
    navigateToLandingScreen: () -> Unit,
    navController: NavController,
//    dateSelectorViewModel: DateSelectorViewModel = viewModel(),
    homeScreenViewModel: HomeScreenViewmodel = viewModel(factory = ViewModelFactory.Factory)

)
{
    val selectedDate by homeScreenViewModel.selectedDate.collectAsState()
    val userState by homeScreenViewModel.userState.collectAsState()
//    val userGroups by homeScreenViewModel.userGroups.collectAsState()
    val groups by homeScreenViewModel.userGroups.collectAsState()
    val selectedGroup by homeScreenViewModel.selectedGroup.collectAsState()


        val context = LocalContext.current
//        val coroutineScope = rememberCoroutineScope()
    val currentUser = FirebaseAuth.getInstance().currentUser

    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            navigateToLandingScreen()
        }
    }


        StandardLayout (navController = navController, content = { modifier ->

    Column(
            modifier = modifier.padding()
        ) {
        DateSelector( Modifier, selectedDate, onDateChange = { newDate ->
            homeScreenViewModel.onDateChange(newDate)})
        ProgressIndicator(modifier, selectedDate, homeScreenViewModel.calculateOverallProgress())

        if (groups.isNotEmpty() && selectedGroup != null) {
            GroupSelect(groups.filterNotNull(), selectedGroup!!) { newGroup ->
                homeScreenViewModel.onGroupChange( newGroup)
            }
        }



        when {
            userState.isLoading -> {
                ProgressBar()
            }

            userState.data.isNotEmpty() -> {



                Column {
                    userState.data.forEach { habit ->
                        if (habit != null) {
                            habit.date = selectedDate.toString()
                            habit.group = selectedGroup.toString()
//                            habit.id
                            Log.d("HomeScreen", "$habit ${habit.id}")
                            selectedGroup?.let { HabitCard(modifier = Modifier, habit = habit, it,
                                selectHabit,
                                homeScreenViewModel::updateHabitToMaxProgress) }
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


