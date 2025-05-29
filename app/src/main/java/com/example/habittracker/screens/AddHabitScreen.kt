package com.example.habittracker.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.habittracker.data.HabitDatabase
import com.example.habittracker.data.HabitEntity
import com.example.habittracker.ui.components.GoalCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(
    habitName: String,
    navController: NavHostController,
    onSave: (String, String) -> Unit = { _, _ -> }
) {
    var goal by remember { mutableStateOf("1 time per day") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habit Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = habitName,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            GoalCard(
                goalText = goal,
                onGoalTextChange = { goal = it },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Button(
                onClick = {
                    val database = HabitDatabase.getDatabase(context)
                    val habit = HabitEntity(name = habitName, goal = goal)

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            database.habitDao().insertHabit(habit)
                            withContext(Dispatchers.Main) {
                                onSave(habitName, goal)
                                navController.popBackStack()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Add Habit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddHabitScreenPreview() {
    val navController = NavHostController(LocalContext.current)
    AddHabitScreen(
        habitName = "Drink Water",
        navController = navController
    )
}