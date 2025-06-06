package com.example.habitapp.presentation.components

import android.icu.math.BigDecimal
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun ProgressIndicator(modifier: Modifier, selectedDate: LocalDate, overallProgress: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().height(300.dp).padding(5.dp)) {
        CircularProgressIndicator(
            progress = { overallProgress },
            modifier = Modifier
                .size(200.dp)
                .padding(5.dp).semantics { contentDescription =
                "progress_indicator"},
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            strokeWidth = 10.dp,
            trackColor = if (selectedDate == LocalDate.now()) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.error,
            strokeCap = StrokeCap.Round,
        )

            Text( "${overallProgress * 100}% complete.",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp

            )

    }


    }
