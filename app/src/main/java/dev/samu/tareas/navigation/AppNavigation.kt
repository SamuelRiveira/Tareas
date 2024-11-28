package dev.samu.tareas.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.samu.tareas.data.AppDatabase
import dev.samu.tareas.screens.ListaTareas
import dev.samu.tareas.screens.Tarea
import dev.samu.tareas.screens.TipoTarea
import dev.samu.tareas.viewmodel.TaskViewModel
import dev.samu.tareas.viewmodel.TypeTaskViewModel

@Composable
fun AppNavigation(modifier: Modifier, taskViewModel: TaskViewModel, typeTaskViewModel: TypeTaskViewModel, database: AppDatabase){
    // estado de gestion de navegación
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.ListaTareas.route) {
        composable(route = AppScreens.ListaTareas.route) {
            Log.d("Prueba", "Lleva a la ruta")
            ListaTareas(navController, taskViewModel)
        }
        composable(route = AppScreens.TipoTarea.route) {
            TipoTarea(navController, typeTaskViewModel)
        }
        composable(route = AppScreens.Tarea.route + "/{text}",
            arguments = listOf(navArgument(name = "text"){
                type = NavType.IntType
            }
            )) {nuevoIndice ->
            val indiceParametro = nuevoIndice.arguments?.getInt("text") ?: 0
            Tarea(
                navController, taskViewModel, typeTaskViewModel, indiceParametro
            )
        }
    }
}
