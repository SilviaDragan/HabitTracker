package com.example.habittracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.habittracker.ui.components.BottomBar
import com.example.habittracker.ui.components.HabitItem
import com.example.habittracker.ui.components.TodayTopBar
import com.example.habittracker.ui.viewmodel.HabitViewModel
import com.example.habittracker.ui.viewmodel.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayScreen(
    navController: NavHostController,
    habitVm: HabitViewModel
) {
    val habits by habitVm.habits.collectAsState()
    LaunchedEffect(habits) {
        println("TodayScreen updated: habits = ${habits.size}")
    }
    val showCongrats by habitVm.showCongrats.collectAsState()

    val quoteVm: QuoteViewModel = viewModel()
    LaunchedEffect(Unit) { quoteVm.loadQuote() }
    val quote = quoteVm.quote.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TodayTopBar() },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        text = if (quote != null) "“${quote.quoteText}”" else "Loading quote…",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                if (habits.isEmpty()) {
                    item {
                        Text(
                            text = "No habits added yet.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                } else {
                    items(habits, key = { it.id }) { habit ->
                        HabitItem(
                            habit = habit,
                            onToggleComplete = { habitVm.completeAndRemoveHabit(it) }
                        )
                    }
                }
            }

            if (showCongrats) {
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {},
                    title = { Text("Great job!!") },
                    text = { Text("Habits are built one action at a time.") }
                )
            }
        }
    }
}