package com.example.habitapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.BottomNavigationDefaults.windowInsets
//import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
//import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitapp.R
import java.time.LocalDate

@Composable
fun DateSelector(navController: NavController, modifier: Modifier,  selectedDate: LocalDate,
                 onDateChange: (LocalDate) -> Unit,) {


    Box(modifier = Modifier.fillMaxWidth()
        .height(42.dp)) {
        Row {
            Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                IconButton(
                    modifier = Modifier.clip(CircleShape),
                    onClick = { onDateChange(selectedDate.minusDays(1))}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Localized description",
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    dateSelector(selectedDate), modifier = Modifier,
                    fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 20.sp )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight() ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { onDateChange(LocalDate.now())}) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Localized description",
                        )
                    }
                    if (selectedDate != LocalDate.now()) {
                        IconButton(onClick = { onDateChange(selectedDate.plusDays(1)) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun dateSelector(date: LocalDate): String {
    return when (date) {
        LocalDate.now() -> stringResource(R.string.today)
        LocalDate.now().minusDays(1) -> stringResource(R.string.yesterday)
        else -> date.toString()
    }
}
