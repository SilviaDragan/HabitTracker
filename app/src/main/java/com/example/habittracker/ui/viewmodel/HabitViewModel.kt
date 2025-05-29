package com.example.habittracker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.HabitDatabase
import com.example.habittracker.data.HabitEntity
import com.example.habittracker.data.HabitRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HabitRepository by lazy {
        val database = HabitDatabase.getDatabase(application)
        HabitRepository(database.habitDao())
    }

    val habits: StateFlow<List<HabitEntity>> = repository.getAllHabits()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _isSaving = MutableLiveData(false)

    private val _saveResult = MutableLiveData<Result<Long>>()
    val saveResult: LiveData<Result<Long>> = _saveResult

    private val _showCongrats = MutableStateFlow(false)
    val showCongrats: StateFlow<Boolean> = _showCongrats

    fun saveHabit(name: String, goal: String) {
        viewModelScope.launch {
            try {
                _isSaving.value = true
                val habit = HabitEntity(
                    name = name,
                    goal = goal
                )
                val habitId = repository.insertHabit(habit)
                _saveResult.value = Result.success(habitId)
            } catch (e: Exception) {
                _saveResult.value = Result.failure(e)
            } finally {
                _isSaving.value = false
            }
        }
    }

    fun toggleHabitCompletion(habit: HabitEntity) {
        viewModelScope.launch {
            try {
                val updatedHabit = habit.copy(
                    isCompleted = !habit.isCompleted,
                    lastCompletedDate = (if (!habit.isCompleted) System.currentTimeMillis() else null).toString()
                )
                repository.updateHabit(updatedHabit)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun completeAndRemoveHabit(habit: HabitEntity) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
            _showCongrats.value = true
            delay(1500)
            _showCongrats.value = false
        }
    }

    fun saveHabitAndWait(name: String, goal: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val initialCount = habits.value.size
            val habit = HabitEntity(name = name, goal = goal)
            repository.insertHabit(habit)

            habits
                .dropWhile { it.size <= initialCount }
                .firstOrNull()

            onComplete()
        }
    }
}