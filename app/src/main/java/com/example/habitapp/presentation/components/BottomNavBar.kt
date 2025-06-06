package com.example.habitapp.presentation.components
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.habitapp.presentation.navigation.NavScreen

@Composable
private fun createListOfItems(): List<NavScreen> {
    return listOf(
        NavScreen.Home,
        NavScreen.Add,
        NavScreen.Exit
    )
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(modifier = Modifier.semantics { contentDescription =
        "bottom_nav"}) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        createListOfItems().forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                modifier = Modifier.semantics { contentDescription = "nav" + item.route },
                        icon = {
                    Icon(
                        if (item.route == currentRoute) item.selectedIcon else item.icon,
                        contentDescription = "nav" + item.route + "_icon",
                        modifier = Modifier
                    )
                },
                label = {
                    Text(
                        text = item.route,
                        fontSize = 9.sp
                    )
                },
                alwaysShowLabel = true,
                onClick = {
                    // If the item clicked is already selected, we may want to ignore it or handle it differently
                    if (currentRoute != item.route) {
                        if (item.route == NavScreen.Home.route) {
                            navController.popBackStack(NavScreen.Home.route, inclusive = false)

                        } else {
                        navController.navigate(item.route) {
                            // Avoid adding the route multiple times in the back stack (especially for Home and Add)
                            popUpTo(navController.graph.startDestinationRoute!!) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                            }
                    }
                }
            )
        }
    }
}