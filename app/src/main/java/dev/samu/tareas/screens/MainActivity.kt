package dev.samu.tareas.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.samu.tareas.data.AppDatabase
import dev.samu.tareas.navigation.AppNavigation
import dev.samu.tareas.ui.theme.TareasTheme
import dev.samu.tareas.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.Companion.getDatabase(this)
        val taskViewModel by viewModels<TaskViewModel>()
        enableEdgeToEdge()
        setContent {
            TareasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        taskViewModel,
                        database
                    )
                }
            }
        }
    }
}