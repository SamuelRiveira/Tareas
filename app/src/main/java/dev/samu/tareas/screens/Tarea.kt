package dev.samu.tareas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.samu.tareas.viewmodel.TaskViewModel

@Composable
fun Tarea(navController: NavHostController, taskViewModel: TaskViewModel, indice: Int?) {
    Text(text = "${taskViewModel.task}")
    LazyColumn {
        itemsIndexed(taskViewModel.task) { index,task ->

            if((indice != null) && (indice == index))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
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
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            onClick = {navController.popBackStack()}
        ) {
            Text(text = "Volver a la pantalla principal")
        }
    }
}