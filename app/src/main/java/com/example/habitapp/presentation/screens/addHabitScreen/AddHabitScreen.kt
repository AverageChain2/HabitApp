package com.example.habitapp.presentation.screens.addHabitScreen

//import com.example.habitapp.presentation.screens.homeScreen.viewmodel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.habitapp.presentation.components.CustomButton
import com.example.habitapp.presentation.components.CustomTextFieldHabit
import com.example.habitapp.presentation.screens.StandardLayout
import com.example.habitapp.util.Util.Companion.showMessage
import kotlinx.coroutines.launch

@Composable
fun AddHabitScreen(
    navigateToHomeScreen:()->Unit,

    navController: NavController,
    vm: AddHabitViewModel = viewModel(factory = ViewModelFactory.Factory)
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current



    StandardLayout (navController = navController, content = { modifier ->

        Column(modifier = modifier.padding(16.dp)) {

            Spacer(modifier = Modifier.size(30.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.add_habit),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Column {
                CustomTextFieldHabit(
                    focusManager = focusManager,
                    stringResource(R.string.habit_unit_hint),
                    text = vm.unit,
                    onNameChange = { vm.unit = it },
                    errorMessage = stringResource(R.string.habit_unit_error),
                    vm.unitIsValid()
                )

                CustomTextFieldHabit(
                    focusManager = focusManager,
                    stringResource(R.string.habit_goal_hint),
                    text = vm.goal.toString(),
                    onNameChange = { vm.goal = it },
                    errorMessage = stringResource(R.string.habit_goal_error),
                    vm.goalIsValid(),
                    isNumeric = true
                )

                CustomTextFieldHabit(
                    focusManager = focusManager,
                    stringResource(R.string.habit_timeframe_hint),
                    text = vm.timeframe.toString(),
                    onNameChange = { vm.timeframe = it },
                    errorMessage = stringResource(R.string.habit_timeframe_error),
                    vm.timeframeIsValid(),
                    isNumeric = true
                )

                CustomTextFieldHabit(
                    focusManager = focusManager,
                    stringResource(R.string.habit_group_hint),
                    text = vm.group,
                    onNameChange = { vm.group = it },
                    errorMessage = stringResource(R.string.habit_group_error),
                    vm.groupIsValid()
                )

                CustomButton(
                    stringResource(R.string.add_button),
                    clickButton = {
                        coroutineScope.launch {
                            val habitAddedSuccessfully = vm.addHabit()
                            if (habitAddedSuccessfully) {
                                keyboardController?.hide()

                                navigateToHomeScreen()
                            } else {
                                showMessage(context, "Failed to add habit")
                            }

                        }

                    }
                )
            }
        }
    })
}