package com.example.habittracker.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.habittracker.ui.components.BottomBar
import com.example.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Profile Screen")
            }
        }
    }
}

@Composable
fun ProfileScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    HabitTrackerTheme {
        SettingsScreen(navController)
    }
}