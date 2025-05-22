package com.example.habittracker.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Habit(
    val id: Int,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
