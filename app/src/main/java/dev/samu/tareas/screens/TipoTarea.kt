package dev.samu.tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import dev.samu.tareas.navigation.AppScreens
import dev.samu.tareas.viewmodel.TypeTaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipoTarea(navController: NavHostController, typeTaskViewModel: TypeTaskViewModel) {
    val selectedTask = typeTaskViewModel.typeTaskList
    var textoTitulo by remember { mutableStateOf(typeTaskViewModel.typeTaskList.title ?: "") }

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
            }
        }

        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route

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
