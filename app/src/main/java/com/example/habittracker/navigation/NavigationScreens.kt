package com.example.sportsnetwork.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.Navigation

sealed class NavigationScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : NavigationScreens("home", "Home", Icons.Default.Home)
    data object Habits : NavigationScreens("habits", "Habits", Icons.Default.Share)
    data object Profile : NavigationScreens("profile", "Profile", Icons.Default.AccountCircle)
}
