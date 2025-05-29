package com.example.habittracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : NavigationScreens("home", "Home", Icons.Default.Home)
    data object Habits : NavigationScreens("habits", "Habits", Icons.Default.Add)
    data object Profile : NavigationScreens("settings", "Settings", Icons.Default.Settings)
}
