package com.example.habittracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.data.HabitEntity
import com.example.habittracker.ui.components.BottomBar
import com.example.habittracker.ui.viewmodel.HabitViewModel
import com.example.habittracker.ui.viewmodel.QuoteViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog

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
@Composable
fun HabitItem(
    habit: HabitEntity,
    onToggleComplete: (HabitEntity) -> Unit
) {
    val shape: Shape = RoundedCornerShape(12.dp)
    val backgroundColor: Color = if (habit.isCompleted) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(backgroundColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = habit.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = habit.goal,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Checkbox(
            checked = habit.isCompleted,
            onCheckedChange = { onToggleComplete(habit) }
        )
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