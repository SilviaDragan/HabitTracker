package com.example.habittracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Insert
    suspend fun insertHabit(habit: HabitEntity): Long

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Query("UPDATE habits SET isCompleted = :isCompleted, lastCompletedDate = :date WHERE id = :habitId")
    suspend fun markHabitComplete(habitId: Long, isCompleted: Boolean, date: String)

    @Query("SELECT * FROM habits")
    fun getAllHabitsFlow(): Flow<List<HabitEntity>>


}