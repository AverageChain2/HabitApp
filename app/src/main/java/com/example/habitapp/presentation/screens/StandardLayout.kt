package com.example.habitapp.presentation.screens


import android.annotation.SuppressLint
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.habitapp.presentation.components.BottomNavBar
import com.example.habitapp.presentation.components.FloatingButton
import com.example.habitapp.presentation.components.TopAppBar
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habitapp.presentation.navigation.NavScreen
import com.example.habitapp.presentation.theme.ThemeModeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandardLayout(
    modifier: Modifier = Modifier,
    navController: NavController,
    content: @Composable (Modifier) -> Unit
) {
    val viewModel: ThemeModeViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navController = navController,
                    isDarkTheme = viewModel.isDarkTheme,
                    onModeChange = { viewModel.onModeChange() }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingButton(
                    "woop",
                    clickAction = { navController.navigate(NavScreen.Add.route) }
                )
            },
            bottomBar = {
                BottomNavBar(
                    navController = navController,
//                    modifier = Modifier
                )
            },
            content = {
                content(Modifier.padding(it).fillMaxHeight().fillMaxWidth().verticalScroll(rememberScrollState()) ) // Pass modifier to the composable
            },
            modifier = modifier
        )
    }
}
