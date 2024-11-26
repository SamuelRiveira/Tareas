package dev.samu.tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.samu.tareas.data.Task
import dev.samu.tareas.navigation.AppScreens
import dev.samu.tareas.viewmodel.TaskViewModel

@Composable
fun ListaTareas(navController: NavHostController, taskViewModel: TaskViewModel) {
    var indiceTarea by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier.background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Tareas",
                fontSize = 30.sp,
                color = Color(0xffc5c5c5),
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(taskViewModel.task) { index, task ->
                    indiceTarea = index
                    Column(modifier = Modifier) {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .height(150.dp)
                                .width(200.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xff242424),
                            ),
                            onClick = {
                                navController.navigate(route = AppScreens.Tarea.route + "/${index}")
                            }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "${task.title}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(bottom = 8.dp)
                                )
                                Text(
                                    text = "${task.content}",
                                    color = Color(0xff828282)
                                )
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                taskViewModel.addTask(Task(title = "Nueva Tarea", content = ""))
                navController.navigate(route = AppScreens.Tarea.route + "/${indiceTarea + 1}")
            },
            containerColor = Color(0xffFFA500),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(45.dp)
                .padding(bottom = 95.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Crear")
        }

        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(100.dp),
            actions = {
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
                }
            },
            containerColor = Color(0xff242424)
        )
    }
}