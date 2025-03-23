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
fun BottomNavBar(navController: NavController, enabled: Boolean, modifier: Modifier) {
    val itemsList = createListOfItems(enabled)

    NavigationBar(
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        itemsList.forEach { item ->
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
                            popUpTo(it) {
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

@Composable
private fun createListOfItems(enabled: Boolean): List<NavScreen> {
    return if(enabled){
        listOf(
            NavScreen.Home,
            NavScreen.Add,
            NavScreen.Exit
        )
    }
    else{
        listOf(
            NavScreen.Exit
        )
    }
}