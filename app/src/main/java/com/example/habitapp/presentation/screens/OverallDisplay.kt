package com.example.habitapp.presentation.screens
//
//
//import android.annotation.SuppressLint
//import androidx.compose.material3.FabPosition
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.habitapp.presentation.components.BottomNavBar
//import com.example.habitapp.presentation.components.FloatingButton
//import com.example.habitapp.presentation.components.TopAppBar
//import com.example.habitapp.presentation.navigation.NavScreen.Add.route
//import com.example.habitapp.presentation.navigation.NavigationGraph
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Surface
//import com.example.habitapp.presentation.theme.AppTheme
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun OverallDisplay(modifier: Modifier = Modifier,
//                   navController: NavHostController = rememberNavController()) {
//    val context = LocalContext.current.applicationContext
//    var loginAuthenticated by remember { mutableStateOf(false) }
//    var isDarkTheme by remember { mutableStateOf(false) }
//
//    AppTheme (darkTheme = isDarkTheme) {
//        Surface( // A surface container using the 'background' color from the theme
//            modifier = Modifier.fillMaxSize(),
//
//            ) {
//            Scaffold(
//                topBar = {
//                    if (loginAuthenticated) {
//                        TopAppBar(navController = navController, isDarkTheme, onModeChange = { theme ->
//                            isDarkTheme = theme})
//                    }
//                },
//                floatingActionButtonPosition = FabPosition.End,
//                floatingActionButton = {
//                    FloatingButton("woop",
//                        {
//                            navController.navigate(route) {
//                                navController.graph.startDestinationRoute?.let {
//                                    popUpTo(it) {
//                                        saveState = true
//                                    }
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        })
//                },
//                modifier = modifier,
//                bottomBar = {
//                    BottomNavBar(
//                        navController = navController,
//                        enabled = loginAuthenticated,
//                        modifier = Modifier
//                    )
//                },
//                content = { padding ->
//                    NavigationGraph(
//                        navController = navController,
//                        context = LocalContext.current,
//                        simulateLogin = {
//                            loginAuthenticated = true
//                            navController.navigate("home")
//                        },
//                        logout = {
//                            loginAuthenticated = false
//                            navController.navigate("login")
//                        },
//                        modifier = Modifier.padding(padding).fillMaxHeight().fillMaxWidth()
//                    )
//
//                }
//
//            )
//        }}
//
////    NavigationGraph(navController = navController,
////        context,
////        simulateLogin = { loginAuthenticated=true
////            navController.navigate("home") },
////        modifier)
//}