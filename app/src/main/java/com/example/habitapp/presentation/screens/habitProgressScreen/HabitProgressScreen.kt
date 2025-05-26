package com.example.habitapp.presentation.screens.habitProgressScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.habitapp.R
import com.example.habitapp.ViewModelFactory
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.presentation.components.CustomButton
import com.example.habitapp.presentation.components.CustomTextFieldHabit
import com.example.habitapp.presentation.components.ProgressBar
import com.example.habitapp.presentation.components.ProgressIndicator
import com.example.habitapp.presentation.screens.habitProgressScreen.viewmodel.HabitProgressScreenViewModel
import com.example.habitapp.util.Util.Companion.showMessage
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitProgressScreen(
    selectedHabit2: Habit,
    vm: HabitProgressScreenViewModel = viewModel(factory = ViewModelFactory.Factory),
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToEditScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val selectedHabit by vm.selectedHabit.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = selectedHabit2) {
        vm.getSingleHabit(selectedHabit2)
    }

//    val progress by vm.progress.collectAsState()


    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(

                {
                    Row(
                         modifier.padding()){
                        IconButton(onClick = {
                            navigateBack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button),
                            )
                        }

                        IconButton(onClick = {
                            navigateToEditScreen()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = stringResource(R.string.Edit),
                            )
                        }
                        if (selectedHabit.data?.suspended == true) {
                            IconButton(onClick = {
                                vm.suspendHabit()
                            }
                                ) {
                                Icon(
                                    imageVector = Icons.Filled.Refresh,
                                    contentDescription = stringResource(R.string.unsupend)
                                )
                            }
                            IconButton(onClick = {
                                vm.deleteHabit()
                                navigateToHomeScreen()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = stringResource(R.string.delete),
                                )
                            }
                        } else {
                            if (selectedHabit.data?.date == LocalDate.now().toString()) {
                                IconButton(onClick = {
                                    vm.suspendHabit()
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayArrow,
                                        contentDescription = stringResource(R.string.suspend),
                                    )
                                }
                            }
                        }


                    }
                    }




            )

}

    ) { innerPadding ->

        when {
            selectedHabit.isLoading -> {
                ProgressBar()
            }

            selectedHabit.data?.id != null -> {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    if (selectedHabit.data?.suspended == true) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.suspended),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (selectedHabit2.date != null) {
                        ProgressIndicator(
                            modifier,
                            LocalDate.parse(selectedHabit2.date),
                            vm.calculateProgress()
                        )

                    }

                    Spacer(modifier = Modifier.size(30.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.update_progress_habit),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "${selectedHabit.data!!.goal} "  +
                                (if (selectedHabit.data!!.goal < 2) selectedHabit.data!!.unit
                                else if (selectedHabit.data!!.unit.endsWith("s")) selectedHabit.data!!.unit
                                else selectedHabit.data!!.unit + "s")
                                        + " in " + "${selectedHabit.data!!.timeframe} " +
                                (if (selectedHabit.data!!.goal < 2) "day"
                                        else "days"),

                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Column {
                        CustomTextFieldHabit(
                            focusManager = focusManager,
                            hintText = stringResource(R.string.habit_progress_hint),
                            text = vm.progress,
                            onNameChange = { vm.progress = it },
                            errorMessage = stringResource(R.string.habit_progress_error),
                            errorPresent = !vm.progressIsValid(),
                            isNumeric = true
                        )


                        CustomButton(
                            stringResource(R.string.update_habit),
                            clickButton = {
                                coroutineScope.launch {
                                    vm.updateHabit()
                                    keyboardController?.hide()
                                }
                            }
                        )
                    }
                }
            }



            selectedHabit.errorMessage.isNotBlank() -> {
                showMessage(context, selectedHabit.errorMessage)

                    navigateToHomeScreen()

            }
        }


    }
}