package com.example.habitapp.presentation.screens.editHabitScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.habitapp.R
import com.example.habitapp.ViewModelFactory
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.presentation.components.CustomButton
import com.example.habitapp.presentation.components.CustomTextFieldAdd
import com.example.habitapp.presentation.screens.editHabitScreen.viewmodel.EditViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditHabitScreen(
    selectedHabit: Habit,
    vm: EditViewModel = viewModel(factory = ViewModelFactory.Factory),
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToHomeScreen: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = selectedHabit) {
        vm.setSelectedHabit(selectedHabit)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(

                {
                    IconButton(onClick = {
//                        selectedHabit = null
//                        navigateToHomeScreen()
                        navigateBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button),
                        )
                    }
                }
            )

        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(innerPadding)
        ) {

            Spacer(modifier = Modifier.size(30.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.edit_habit),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Column {
                CustomTextFieldAdd(
                    focusManager = focusManager,
                    stringResource(R.string.habit_unit_hint),
                    text = vm.unit,
                    onNameChange = { vm.unit = it },
                    errorMessage = stringResource(R.string.habit_unit_error),
                    vm.unitIsValid()
                )
                CustomTextFieldAdd(
                    focusManager = focusManager,
                    stringResource(R.string.habit_goal_hint),
                    text = vm.goal,
                    onNameChange = { vm.goal = it },
                    errorMessage = stringResource(R.string.habit_goal_error),
                    vm.goalIsValid(),
                    isNumeric = true
                )
                CustomTextFieldAdd(
                    focusManager = focusManager,
                    stringResource(R.string.habit_timeframe_hint),
                    text = vm.timeframe,
                    onNameChange = { vm.timeframe = it },
                    errorMessage = stringResource(R.string.habit_timeframe_error),
                    vm.timeframeIsValid(),
                    isNumeric = true
                )
                CustomTextFieldAdd(
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
                            if (selectedHabit.group != vm.group) {
                                navigateToHomeScreen()
                            } else {
                                navigateBack()
                            }
                        }
                    }
                )
            }
        }
    }
}