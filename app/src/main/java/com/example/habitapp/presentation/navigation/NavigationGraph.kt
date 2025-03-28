package com.example.habitapp.presentation.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.habitapp.R
import com.example.habitapp.presentation.screens.AddScreen
import com.example.habitapp.presentation.screens.HomeScreen
import com.example.habitapp.presentation.screens.LoginScreen
import com.example.habitapp.presentation.screens.Register.RegisterScreen

open class NavScreen(var icon:ImageVector, var selectedIcon: ImageVector, var route:String){
    object Login : NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Login")
    object Register : NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Register")
    object Home : NavScreen(Icons.Outlined.Home, Icons.Filled.Home, "Home")
    object Add: NavScreen(Icons.Outlined.Add, Icons.Filled.Add, "Add")
    object Exit: NavScreen(Icons.Outlined.Lock, Icons.Filled.Lock, "Logout")
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    context: Context,
    simulateLogin: () -> Unit,
    logout: () -> Unit,
    modifier: Modifier
) {
    NavHost(navController,
        startDestination = NavScreen.Register.route) {
        composable(NavScreen.Login.route) {
            LoginScreen(stringResource(R.string.login_button), simulateLogin, modifier)
        }
        composable(NavScreen.Register.route) {
            RegisterScreen(stringResource(R.string.register_button), modifier)
        }
        composable(NavScreen.Home.route) {
            HomeScreen(stringResource(R.string.home_button), modifier, navController)
        }
        composable(NavScreen.Add.route) {
            AddScreen(stringResource(R.string.add_button), modifier)
        }
        composable(NavScreen.Exit.route) {
            logout()
        }

    }
}
