package dev.samu.tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.samu.tareas.viewmodel.TaskViewModel
import dev.samu.tareas.R
import dev.samu.tareas.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tarea(navController: NavHostController, taskViewModel: TaskViewModel, indice: Int?) {
    val selectedTask = indice?.let { taskViewModel.task.getOrNull(it) }
    var textoTitulo by remember { mutableStateOf(selectedTask?.title ?: "") }
    var textoContenido by remember { mutableStateOf(selectedTask?.content ?: "") }

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
                        contentDescription = "Volver atrás",
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
                IconButton(onClick = {
                    selectedTask?.let { task ->
                        taskViewModel.deleteTask(task)
                        navController.navigate(route = AppScreens.ListaTareas.route)
                    }
                }) {
                    Icon(
                        painter = painterResource(R.drawable.eliminar),
                        contentDescription = "Eliminar",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                IconButton(onClick = {
                    selectedTask?.let { task ->
                        val updatedTask = task.copy(title = textoTitulo, content = textoContenido)
                        taskViewModel.updateTask(updatedTask)
                        navController.popBackStack()
                    }
                }) {
                    Icon(
                        painter = painterResource(R.drawable.editar),
                        contentDescription = "Editar",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        )
        LazyColumn {
            itemsIndexed(taskViewModel.task) { index, task ->
                if (indice != null && indice == index) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 16.dp)
                    ) {
                        TextField(
                            value = textoTitulo,
                            onValueChange = { textoTitulo = it },
                            textStyle = TextStyle(
                                fontSize = 30.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                        )
                        TextField(
                            value = textoContenido,
                            onValueChange = { textoContenido = it },
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                color = Color.White
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                        )
                    }
                }
            }
        }
    }
}
