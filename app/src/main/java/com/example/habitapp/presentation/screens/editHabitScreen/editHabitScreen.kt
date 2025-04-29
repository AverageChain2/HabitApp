package com.example.habitapp.presentation.screens.editHabitScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habitapp.R
import com.example.habitapp.ViewModelFactory
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.presentation.components.CustomButton
import com.example.habitapp.presentation.components.CustomTextField
import com.example.habitapp.presentation.screens.OverallDisplay
import kotlinx.coroutines.launch

@Composable
fun EditHabitScreen(
    navController: NavController,
    selectedHabit: Habit,
    vm: EditViewModel = viewModel(factory = ViewModelFactory.Factory),
    navigateToHomeScreen: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) { // Called on launch
        vm.setSelectedHabit(selectedHabit)
    }

    OverallDisplay(navController = navController, content = { modifier ->
        Column(modifier = modifier.padding(16.dp)) {

            Spacer(modifier = Modifier.size(30.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.edit_habit),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Column {
                CustomTextField(
                    focusManager = focusManager,
                    stringResource(R.string.habit_unit_hint),
                    text = vm.unit,
                    onNameChange = { vm.unit = it },
                    errorMessage = stringResource(R.string.habit_unit_error),
                    vm.unitIsValid()
                )

                CustomTextField(
                    focusManager = focusManager,
                    stringResource(R.string.habit_goal_hint),
                    text = vm.goal,
                    onNameChange = { vm.goal = it },
                    errorMessage = stringResource(R.string.habit_goal_error),
                    vm.goalIsValid()
                )

                CustomTextField(
                    focusManager = focusManager,
                    stringResource(R.string.habit_timeframe_hint),
                    text = vm.timeframe,
                    onNameChange = { vm.timeframe = it },
                    errorMessage = stringResource(R.string.habit_timeframe_error),
                    vm.timeframeIsValid()
                )

                CustomTextField(
                    focusManager = focusManager,
                    stringResource(R.string.habit_group_hint),
                    text = vm.group,
                    onNameChange = { vm.group = it },
                    errorMessage = stringResource(R.string.habit_group_error),
                    vm.groupIsValid()
                )

                CustomButton(
                    stringResource(R.string.update_habit),
                    clickButton = {
                        coroutineScope.launch {
                            vm.updateHabit()
                            keyboardController?.hide()
                            navigateToHomeScreen()
                        }
                    }
                )
            }
        }
    })
}