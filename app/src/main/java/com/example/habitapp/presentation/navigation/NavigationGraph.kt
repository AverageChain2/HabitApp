package com.example.habitapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitapp.HabitApplication
import com.example.habitapp.R
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.presentation.screens.addHabitScreen.AddHabitScreen
import com.example.habitapp.presentation.screens.editHabitScreen.EditHabitScreen
import com.example.habitapp.presentation.screens.habitProgressScreen.HabitProgressScreen
//import com.example.habitapp.presentation.screens.addScreen.AddScreen
import com.example.habitapp.presentation.screens.homeScreen.HomeScreen
import com.example.habitapp.presentation.screens.landingScreen.LandingScreen
import com.example.habitapp.presentation.screens.loginScreen.LoginScreen
import com.example.habitapp.presentation.screens.registerScreen.RegisterScreen


sealed class NavScreen(var icon:ImageVector, var selectedIcon: ImageVector, var route:String){
    data object Landing: NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Landing")
    data object Login : NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Login")
    data object Register: NavScreen(Icons.Outlined.Person, Icons.Filled.Person, "Register")
    data object Home : NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Home")
    data object Add: NavScreen(Icons.Outlined.Add, Icons.Filled.Add, "Add")
    data object Progress: NavScreen(Icons.Outlined.Add, Icons.Filled.Add, "Progress")
    data object Edit: NavScreen(Icons.Outlined.Add, Icons.Filled.Add, "Edit")
    data object Exit: NavScreen(Icons.Outlined.Lock, Icons.Filled.Lock, "Exit")
}


@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController()
) {
//    val currentUser = FirebaseAuth.getInstance().currentUser
    val startDestination = NavScreen.Landing.route
    var selectedHabit: Habit? = null

    NavHost(navController, startDestination = if (HabitApplication.getAuthRepository().currentUser != null) {NavScreen.Home.route}
    else {NavScreen.Landing.route}) {

        composable(NavScreen.Landing.route) {
            LandingScreen(
                navigateToLoginScreen = {
                    navController.navigate(NavScreen.Login.route) {
                    }
                },
                navigateToRegisterScreen = { navController.navigate(NavScreen.Register.route)}

//                    navigateToHomeScreen = {
//                        navController.navigate(NavScreen.Home.route){
//                            popUpTo(NavScreen.Landing.route) { inclusive = true }
//                            launchSingleTop = true
//                        }}
            )
        }

        composable(NavScreen.Login.route) {
            LoginScreen(
                navigateToHomeScreen = {
                    navController.navigate(NavScreen.Home.route) {
                        popUpTo(NavScreen.Landing.route) { inclusive = true }
                    }
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(NavScreen.Register.route) {
            RegisterScreen(
                navigateBack = { navController.popBackStack() },
                navigateToLogin = {navController.navigate(NavScreen.Login.route)}
            )
        }

        composable(NavScreen.Home.route) {
            HomeScreen(
                stringResource(R.string.home_button),
                selectHabit = { selectedHabit = it
                    navController.navigate(NavScreen.Progress.route) },
                navigateToLandingScreen = {
                    navController.navigate(NavScreen.Home.route)
                },
                navController
            )
        }

        composable(NavScreen.Add.route) {
            AddHabitScreen(
                navigateToHomeScreen = {
                    navController.popBackStack(NavScreen.Home.route, inclusive = false)
                },
                navController = navController
            )
        }

        composable(NavScreen.Progress.route) {
            selectedHabit?.let {
                HabitProgressScreen(
                    selectedHabit2 = it,
                    navigateBack = { navController.popBackStack() },
                    navigateToEditScreen = {
                        navController.navigate(NavScreen.Edit.route)
                    },
                    navigateToHomeScreen = {
                        navController.navigate(NavScreen.Home.route)
                    }
                )
            } ?: navController.navigate(NavScreen.Home.route)
        }

        composable(NavScreen.Edit.route) {
            selectedHabit?.let {
                EditHabitScreen(
                    selectedHabit = it,
                    navigateBack = { navController.popBackStack() },
                    navigateToHomeScreen = {
                        navController.navigate(NavScreen.Home.route)
                    }                )
            } ?: navController.navigate(NavScreen.Home.route)
        }

        composable(NavScreen.Exit.route) {
            if (HabitApplication.getAuthRepository().currentUser != null){
                HabitApplication.getAuthRepository().signOut()
                navController.navigate(NavScreen.Landing.route)
            }
        }
    }
}
