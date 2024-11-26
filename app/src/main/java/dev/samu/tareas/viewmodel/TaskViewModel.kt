package dev.samu.tareas.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import dev.samu.tareas.data.AppDatabase
import dev.samu.tareas.data.Task
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getDatabase(application)

    var task by mutableStateOf(listOf<Task>())
        private set

    init {
        viewModelScope.launch {
            loadTasksFromDatabase()
        }
    }

    private suspend fun loadTasksFromDatabase() {
        task = database.taskDao().getAllTasks()
    }

    fun addTask(newTask: Task) {
        viewModelScope.launch {
            database.taskDao().insert(newTask)
            loadTasksFromDatabase()
        }
    }

    fun updateTask(updatedTask: Task) {
        viewModelScope.launch {
            database.taskDao().update(updatedTask)
            loadTasksFromDatabase()
        }
    }

    fun deleteTask(taskToDelete: Task) {
        viewModelScope.launch {
            database.taskDao().delete(taskToDelete)
            loadTasksFromDatabase()
        }
    }
}

