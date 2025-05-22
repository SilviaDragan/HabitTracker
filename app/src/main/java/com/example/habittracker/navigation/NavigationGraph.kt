package com.example.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.habittracker.screens.AddHabitScreen
import com.example.habittracker.screens.NewHabitScreen
import com.example.habittracker.screens.ProfileScreen
import com.example.habittracker.screens.TodayScreen
import com.example.sportsnetwork.navigation.NavigationScreens

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
        composable(
            route = "habit_detail/{habitName}",
            arguments = listOf(navArgument("habitName") { type = NavType.StringType })
        ) { backStackEntry ->
            val habitName = backStackEntry.arguments?.getString("habitName") ?: ""
            AddHabitScreen(
                habitName = habitName,
                navController = navController,
                onSave = { name, goal -> {} }
            )
        }
    }
}

