package com.example.habitapp.presentation.components


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
//import androidx.compose.material.IconButton
//import androidx.compose.material.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.exp


@Composable
fun GroupSelect() {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.height(50.dp).fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.onBackground)){
        TextButton (onClick = {expanded = true}, modifier = Modifier.fillMaxWidth()) {
            Text("Group Select Button")
            Icon(Icons.Default.MoreVert, contentDescription = "Description")
        }
        DropdownMenu(expanded,
            onDismissRequest = { expanded = false},
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(
                text = { Text("hi") },
                onClick = {},

                )
            DropdownMenuItem(
                text = { Text("hi2") },
                onClick = {},

                )
        }
}
}