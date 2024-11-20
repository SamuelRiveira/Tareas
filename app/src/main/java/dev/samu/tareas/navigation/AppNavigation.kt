package dev.samu.tareas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.samu.tareas.screens.ListaTareas
import dev.samu.tareas.screens.Tarea

@Composable
fun AppNavigation(modifier: androidx.compose.ui.Modifier){
    // estado de gestion de navegación
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.ListaTareas.route) {
        composable(route = AppScreens.ListaTareas.route) {
            ListaTareas(navController)
        }
        composable(route = AppScreens.Tarea.route) {
            Tarea(navController)
        }
//        composable(route = AppScreens.Tarea.route + "/{text}",
//            arguments = listOf(navArgument(name = "text"){
//                type = NavType.StringType
//            }
//            )) {
//            Tarea(navController, it.arguments?.getString("text"))
//        }
    }
}
