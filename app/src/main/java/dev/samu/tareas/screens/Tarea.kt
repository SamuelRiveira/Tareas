package dev.samu.tareas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.samu.tareas.viewmodel.TaskViewModel

@Composable
fun Tarea(navController: NavHostController, taskViewModel: TaskViewModel, indice: Int?) {
    LazyColumn {
        itemsIndexed(taskViewModel.task) { index,task ->

            if((indice != null) && (indice == index))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(top = 16.dp)
            ) {
                Text(
                    text = "${task.title}",
                    fontSize = 20.sp
                )
                Text(text = "Contenido: ${task.content}")
//                Row {
//                    Button(onClick = {
//                        taskViewModel.deleteTask(task)
//                        navController.navigate(route = AppScreens.ListaTareas.route)
//                    }) {
//                        Text(text = "Eliminar")
//                    }
//                    Button(onClick = {
//                        val updatedTask = task.copy(content = "Contenido actualizado")
//                        taskViewModel.updateTask(updatedTask)
//                    }) {
//                        Text(text = "Editar")
//                    }
//                }
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