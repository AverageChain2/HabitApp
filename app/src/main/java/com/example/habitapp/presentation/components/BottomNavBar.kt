package com.example.habitapp.presentation.components
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.habitapp.presentation.navigation.NavScreen

@Composable
private fun createListOfItems (): List<NavScreen> {
    return listOf(
        NavScreen.Home,
        NavScreen.Add,
        NavScreen.Exit
    )
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        createListOfItems().forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,

                icon = {
                    Icon(
                        if (item.route == currentRoute) item.selectedIcon else  item.icon,
                        contentDescription = item.route,
                        modifier = Modifier
                    )
                     },
                label = { Text(text = item.route,
                    fontSize = 9.sp) },
                alwaysShowLabel = true,


                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}