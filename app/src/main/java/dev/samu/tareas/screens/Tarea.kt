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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.samu.tareas.viewmodel.TaskViewModel
import dev.samu.tareas.viewmodel.TypeTaskViewModel
import dev.samu.tareas.R
import dev.samu.tareas.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tarea(navController: NavHostController, taskViewModel: TaskViewModel, typeTaskViewModel: TypeTaskViewModel, indice: Int?) {

    // Obtén la tarea seleccionada
    val selectedTask = indice?.let { taskViewModel.task.getOrNull(it) }
    var textoTitulo by remember { mutableStateOf(selectedTask?.title ?: "") }
    var textoContenido by remember { mutableStateOf(selectedTask?.content ?: "") }

    // Obtener la lista de tipos de tarea desde el ViewModel
    val tiposTareas = typeTaskViewModel.typeTaskList

    val tipoTareaId = selectedTask?.typeTaskId ?: 0
    val tipoTareaSeleccionado = tiposTareas.getOrNull(tipoTareaId)?.name
    var tipoSeleccionado by remember { mutableStateOf(tipoTareaSeleccionado ?: "") }

    var expanded by remember { mutableStateOf(false) }

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
                        val updatedTask = task.copy(
                            title = textoTitulo,
                            content = textoContenido,
                            typeTaskId = tiposTareas.indexOfFirst { it.name == tipoSeleccionado }
                        )
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
                        OutlinedTextField(
                            value = tipoSeleccionado,
                            onValueChange = { tipoSeleccionado = it },
                            label = { Text("Tipo de Tarea", color = Color(0xff828282)) },
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = { expanded = !expanded }) {
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Expandir")
                                }
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.Black
                            ),
                            textStyle = TextStyle(
                                color = Color.White
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Mostrar el DropdownMenu
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            tiposTareas.forEach { tipo ->
                                DropdownMenuItem(
                                    onClick = {
                                        tipoSeleccionado = tipo.name
                                        expanded = false
                                    },
                                    text = {
                                        Text(
                                            text = tipo.name,
                                            color = Color.Black
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
