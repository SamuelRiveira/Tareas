package dev.samu.tareas.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import dev.samu.tareas.data.AppDatabase
import dev.samu.tareas.data.TypeTask
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class TypeTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getDatabase(application)

    var typeTaskList by mutableStateOf(listOf<TypeTask>())
        private set

    init {
        viewModelScope.launch {
            loadTypeTasksFromDatabase()
        }
    }

    private suspend fun loadTypeTasksFromDatabase() {
        typeTaskList = database.typeTaskDao().getAllTypeTasks()
    }

    fun addTypeTask(newTypeTask: TypeTask) {
        viewModelScope.launch {
            database.typeTaskDao().insert(newTypeTask)
            loadTypeTasksFromDatabase()
        }
    }

    fun updateTypeTask(updatedTypeTask: TypeTask) {
        viewModelScope.launch {
            database.typeTaskDao().update(updatedTypeTask)
            loadTypeTasksFromDatabase()
        }
    }

    fun deleteTypeTask(typeTaskToDelete: TypeTask) {
        viewModelScope.launch {
            database.typeTaskDao().delete(typeTaskToDelete)
            loadTypeTasksFromDatabase()
        }
    }
}