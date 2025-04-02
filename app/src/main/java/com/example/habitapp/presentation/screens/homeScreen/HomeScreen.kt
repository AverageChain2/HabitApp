package com.example.habitapp.presentation.screens.homeScreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habitapp.presentation.components.DateSelector
import com.example.habitapp.presentation.components.ProgressIndicator
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habitapp.presentation.components.GroupSelect
import com.example.habitapp.presentation.components.DateSelectorViewModel
import com.example.habitapp.presentation.screens.OverallDisplay

@Composable
fun HomeScreen(
    text: String,
    navController: NavController,
    dateSelectorViewModel: DateSelectorViewModel = viewModel(),

)
{
    val selectedDate by dateSelectorViewModel.selectedDate.observeAsState()
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

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    })
}


