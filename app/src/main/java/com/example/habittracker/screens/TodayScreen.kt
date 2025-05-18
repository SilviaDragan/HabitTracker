package com.example.habittracker.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.ui.components.BottomBar
import com.example.habittracker.ui.theme.HabitTrackerTheme


@Composable
fun TodayScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TodayTopBar()
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Welcome back!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "“The secret of getting ahead is getting started.”",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.today),
                style = MaterialTheme.typography.displayLarge,
            )
        },
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun TodayScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    HabitTrackerTheme {
        TodayScreen(navController)
    }
}