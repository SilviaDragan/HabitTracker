package com.example.habittracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.habittracker.data.HabitEntity


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