package com.example.habitapp.presentation.components


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp


@Composable
fun GroupSelect(groups: List<String>, selectedGroup: String, onGroupChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.height(50.dp).fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.onBackground)){
        TextButton (onClick = {expanded = true}, modifier = Modifier.fillMaxWidth()) {
            Text(selectedGroup)
            Icon(Icons.Default.MoreVert, contentDescription = "Description")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, Modifier.semantics{contentDescription = "group_select"}) {
            groups.forEach { group ->
                DropdownMenuItem(
                    text = { Text(group) },
                    onClick = {
                        onGroupChange(group)
                        expanded = false }
                )
            }
        }

    }
}