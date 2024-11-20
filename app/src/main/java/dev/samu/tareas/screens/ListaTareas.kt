package dev.samu.tareas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.samu.tareas.data.Task
import dev.samu.tareas.navigation.AppScreens
import dev.samu.tareas.viewmodel.TaskViewModel

@Composable
fun ListaTareas(navController: NavHostController, taskViewModel: TaskViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            taskViewModel.addTask(Task(title = "Nueva Tarea", content = "Contenido de prueba"))
        }) {
            Text(text = "Agregar tarea")
        }

        LazyColumn {
            items(taskViewModel.task) { task ->
                Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Text(text = "Título: ${task.title}")
                    Text(text = "Contenido: ${task.content}")
                    Row {
                        Button(onClick = { taskViewModel.deleteTask(task) }) {
                            Text(text = "Eliminar")
                        }
                        Button(onClick = {
                            val updatedTask = task.copy(content = "Contenido actualizado")
                            taskViewModel.updateTask(updatedTask)
                        }) {
                            Text(text = "Editar")
                        }
                    }
                }
            }
        }

        Button(onClick = { navController.navigate(route = AppScreens.Tarea.route) }) {
            Text(text = "Ir a la segunda pantalla")
        }
    }
}