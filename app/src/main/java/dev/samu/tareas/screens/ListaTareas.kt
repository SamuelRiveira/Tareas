package dev.samu.tareas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.samu.tareas.navigation.AppScreens

@Composable
fun ListaTareas(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Página principal")
        Button(
            onClick = {navController.navigate(route = AppScreens.Tarea.route)}
        ) {
            Text(text = "Ir a la segunda pantalla")
        }
    }
}