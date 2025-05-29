package com.example.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.habittracker.ui.screens.AddHabitScreen
import com.example.habittracker.ui.screens.HabitsScreen
import com.example.habittracker.ui.screens.SettingsScreen
import com.example.habittracker.ui.screens.TodayScreen
import com.example.habittracker.ui.viewmodel.HabitViewModel

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habittracker.ui.screens.AddCustomHabitScreen
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    habitVm: HabitViewModel,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.Home.route
    ) {
        composable(route = NavigationScreens.Home.route) {
            TodayScreen(
                navController = navController,
                habitVm = habitVm
            )
        }

        composable(route = NavigationScreens.Habits.route) {
            HabitsScreen(navController = navController)
        }

        composable(route = NavigationScreens.Settings.route) {
            SettingsScreen(
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }

        composable(
            route = "habit_detail/{habitName}",
            arguments = listOf(navArgument("habitName") {
                type = NavType.StringType
            })
        ) { backStack ->
            val name = backStack.arguments?.getString("habitName") ?: ""
            AddHabitScreen(
                habitName = name,
                navController = navController,
                habitVm = habitVm,
                onSave = { _, _ ->
                    navController.navigate(NavigationScreens.Home.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = "habit_detail/custom") {
            AddCustomHabitScreen(
                navController = navController,
                habitVm = habitVm,
                onSave = { _, _ ->
                    navController.navigate(NavigationScreens.Home.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}