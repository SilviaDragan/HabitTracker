package com.example.habittracker.model

import com.example.habittracker.R


object HabitsRepository {
    val habits = listOf(
        Habit(
            nameRes = R.string.habit1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1
        ),
        Habit(
            nameRes = R.string.habit2,
            descriptionRes = R.string.description2,
            imageRes = R.drawable.android_superhero2
        ),
        Habit(
            nameRes = R.string.habit3,
            descriptionRes = R.string.description3,
            imageRes = R.drawable.android_superhero3
        ),
        Habit(
            nameRes = R.string.habit4,
            descriptionRes = R.string.description4,
            imageRes = R.drawable.android_superhero4
        ),
        Habit(
            nameRes = R.string.habit5,
            descriptionRes = R.string.description5,
            imageRes = R.drawable.android_superhero5
        ),
        Habit(
            nameRes = R.string.habit6,
            descriptionRes = R.string.description6,
            imageRes = R.drawable.android_superhero6
        )
    )
}
