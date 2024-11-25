package dev.samu.tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.samu.tareas.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tarea(navController: NavHostController, taskViewModel: TaskViewModel, indice: Int?) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
                }
            },
            title = {
                Text(text = "")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            actions = {
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                }
            }
        )
        LazyColumn {
            itemsIndexed(taskViewModel.task) { index,task ->

                if((indice != null) && (indice == index))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                    ) {
                        Text(
                            text = "${task.title}",
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 16.dp, bottom =16.dp)
                        )
                        Text(
                            text = "${task.content}",
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 16.dp)
                        )
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
    }
}