package com.example.habittracker.model

import com.example.habittracker.R

object HabitsRepository {
    val habits = listOf(
        Habit(
            id = 1,
            nameRes = R.string.habit1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.workout_icon
        ),
        Habit(
            id = 2,
            nameRes = R.string.habit3,
            descriptionRes = R.string.description3,
            imageRes = R.drawable.bed_icon
        ),
        Habit(
            id = 3,
            nameRes = R.string.habit4,
            descriptionRes = R.string.description4,
            imageRes = R.drawable.reading_icon
        ),
        Habit(
            id = 4,
            nameRes = R.string.habit5,
            descriptionRes = R.string.description5,
            imageRes = R.drawable.walk_icon
        ),
        Habit(
            id = 5,
            nameRes = R.string.habit6,
            descriptionRes = R.string.description6,
            imageRes = R.drawable.study_icon
        ),
        Habit(
            id = 6,
            nameRes = R.string.habit2,
            descriptionRes = R.string.description2,
            imageRes = R.drawable.water_icon
        )
    )
}