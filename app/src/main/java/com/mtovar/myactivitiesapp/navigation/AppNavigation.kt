package com.mtovar.myactivitiesapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mtovar.myactivitiesapp.ui.ActivityViewModel
import com.mtovar.myactivitiesapp.ui.screens.AddActivityScreen
import com.mtovar.myactivitiesapp.ui.screens.HomeScreen

sealed class AppScreen(val route: String) {
    object Home : AppScreen("home")
    object AddActivity : AppScreen("add_activity")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Compartimos una unica instancia del ViewModel entre ls dos pantallas
    val activityViewModel: ActivityViewModel = viewModel()

    NavHost(navController = navController, startDestination = AppScreen.Home.route) {
        composable(AppScreen.Home.route) {
            HomeScreen(
                viewModel = activityViewModel,
                onAddActivityClick = {
                    navController.navigate(AppScreen.AddActivity.route)
                })
        }
        composable(AppScreen.AddActivity.route) {
            AddActivityScreen(
                viewModel = activityViewModel,
                onActivityAdded = {
                    navController.popBackStack() // Regresa a la pantalla anterior
                })
        }
    }
}