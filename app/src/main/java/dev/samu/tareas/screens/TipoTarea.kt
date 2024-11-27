package dev.samu.tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.samu.tareas.data.Task
import dev.samu.tareas.data.TypeTask
import dev.samu.tareas.navigation.AppScreens
import dev.samu.tareas.viewmodel.TypeTaskViewModel
import java.lang.reflect.Type
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipoTarea(navController: NavHostController, typeTaskViewModel: TypeTaskViewModel) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var textoTitulo by remember { mutableStateOf("Tipo") }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .padding(top = 16.dp)
        ) {
            Column {
                Row {
                    TextField(
                        value = textoTitulo,
                        onValueChange = { textoTitulo = it }
                    )
                    Button(
                        onClick = {
                            expanded = true
                        }
                    ) {
                        Text(text = "ver tipos")
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ){
                    typeTaskViewModel.typeTaskList.forEach { tipo ->
                        DropdownMenuItem(
                            text = {
                                Text(text = "${tipo.name}")
                            },
                            onClick = {
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                typeTaskViewModel.addTypeTask(TypeTask(name = textoTitulo))
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
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Edit, "Tareas", tint = Color.White) },
                    label = { Text("Tareas", color = Color.White) },
                    selected = currentRoute?.startsWith(AppScreens.ListaTareas.route) == true,
                    onClick = {
                        if (currentRoute != AppScreens.ListaTareas.route) {
                            navController.navigate(AppScreens.ListaTareas.route)
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Info, "Tipos", tint = Color.White) },
                    label = { Text("Tipos", color = Color.White) },
                    selected = currentRoute == AppScreens.TipoTarea.route,
                    onClick = {
                        if (currentRoute != AppScreens.TipoTarea.route) {
                            navController.navigate(AppScreens.TipoTarea.route)
                        }
                    }
                )
            },
            containerColor = Color(0xff242424)
        )
    }
}
