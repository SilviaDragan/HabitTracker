package com.example.sportsnetwork.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.habittracker.screens.MainScreen
import com.example.habittracker.screens.NewHabitScreen
import com.example.habittracker.screens.ProfileScreen
import com.example.habittracker.screens.TodayScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.Home.route
    ) {
        composable(route = NavigationScreens.Home.route) {
            TodayScreen(navController = navController)
        }
        composable(route = NavigationScreens.Habits.route) {
            NewHabitScreen(navController = navController)
        }
        composable(route = NavigationScreens.Profile.route) {
            ProfileScreen(navController = navController)
        }
//        composable(route = NavigationScreens.Profile.route) {
//            when (authViewModel.authState.value) {
//                AuthViewModel.AuthState.Unauthenticated -> {
//                    ProfileScreenNoAuth(navController, authViewModel)
//                }
//                AuthViewModel.AuthState.Authenticated -> {
//                    ProfileScreenAuth(navController, authViewModel)
//                }
//                else -> {
//                    Text("Loading")
//                }
//            }
//        }
    }
}

