package com.example.habittracker.data

import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {
    fun getAllHabits(): Flow<List<HabitEntity>> = habitDao.getAllHabits()

    suspend fun insertHabit(habit: HabitEntity): Long = habitDao.insertHabit(habit)

    suspend fun updateHabit(habit: HabitEntity) = habitDao.updateHabit(habit)

    suspend fun deleteHabit(habit: HabitEntity) = habitDao.deleteHabit(habit)

    suspend fun markHabitComplete(habitId: Long, isCompleted: Boolean, date: String) =
        habitDao.markHabitComplete(habitId, isCompleted, date)

    suspend fun getHabits(): List<HabitEntity> {
        return habitDao.getAllHabits() as List<HabitEntity>
    }
}