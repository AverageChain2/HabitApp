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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitapp.R
import com.example.habitapp.presentation.screens.AddScreen
import com.example.habitapp.presentation.screens.HomeScreen
import com.example.habitapp.presentation.screens.landingScreen.LandingScreen
import com.example.habitapp.presentation.screens.login.LoginScreen
import com.example.habitapp.presentation.screens.registerScreen.RegisterScreen

sealed class NavScreen(var icon:ImageVector, var selectedIcon: ImageVector, var route:String){
    data object Landing: NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Landing")
    data object Login : NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Login")
    data object Register: NavScreen(Icons.Outlined.Person, Icons.Filled.Person, "Register")
    data object Home : NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Home")
    data object Add: NavScreen(Icons.Outlined.Add, Icons.Filled.Add, "Add")
    data object Exit: NavScreen(Icons.Outlined.Lock, Icons.Filled.Lock, "Logout")
}

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier.testTag("TestNavGraph"),
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController,
        startDestination = NavScreen.Landing.route) {

        composable(NavScreen.Landing.route) {
            LandingScreen(
                navigateToLoginScreen = {
                navController.navigate(NavScreen.Login.route)
            } ,
                    navigateToRegisterScreen = {
                navController.navigate(NavScreen.Register.route)
            }
            )
        }

        composable(NavScreen.Login.route) {
            LoginScreen(navigateToHomeScreen = {
                navController.navigate(NavScreen.Home.route)

            },navigateBack = {navController.popBackStack()}

            )
        }
        composable(NavScreen.Register.route) {
            RegisterScreen(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(NavScreen.Home.route) {
            HomeScreen(stringResource(R.string.home_button), navController)
        }
        composable(NavScreen.Add.route) {
            AddScreen(stringResource(R.string.add_button))
        }
        composable(NavScreen.Exit.route) {
//            ContactApplication.getAuthRepository.signOut()
//            exitProcess(0)
        }

    }
}
