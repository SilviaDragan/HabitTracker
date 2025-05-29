package com.example.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.habittracker.screens.AddHabitScreen
import com.example.habittracker.screens.HabitsScreen
import com.example.habittracker.screens.SettingsScreen
import com.example.habittracker.screens.TodayScreen
import com.example.habittracker.ui.viewmodel.HabitViewModel

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.Home.route
    ) {
        composable(route = NavigationScreens.Home.route) { backStackEntry ->
            val vm: HabitViewModel = viewModel(backStackEntry)
            val habits by vm.habits.collectAsStateWithLifecycle()

            TodayScreen(
                navController = navController,
                habitVm = vm
            )
        }

        composable(route = NavigationScreens.Habits.route) {
            HabitsScreen(navController = navController)
        }

        composable(route = NavigationScreens.Profile.route) {
            SettingsScreen(navController = navController)
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
                onSave = { _, _ ->
                    navController.navigate(NavigationScreens.Home.route) {
                        popUpTo(NavigationScreens.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
