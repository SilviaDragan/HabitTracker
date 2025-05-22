package com.example.habittracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.ui.components.BottomBar
import com.example.habittracker.ui.theme.HabitTrackerTheme
import com.example.habittracker.ui.viewmodel.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayScreen(navController: NavHostController) {
    val quoteVm: QuoteViewModel = viewModel()
    LaunchedEffect(Unit) { quoteVm.loadQuote() }
    val quote = quoteVm.quote.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TodayTopBar() },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Quote Banner
            if (quote != null) {
                Text(
                    text = "Quote of the day:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "“${quote.quoteText}”",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                Text(
                    text = "Loading quote…",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Here i wil display the list of added habits to complete
            // each habit will have a checkbox to mark it as completed
            // get a fun pop-up reaction when completing a habit

            // a habit marked as completed is displayed in a different color and with a completed icon

            // if all habits are completed, show a congratulatory message above all habits.
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