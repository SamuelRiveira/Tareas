package dev.samu.tareas.navigation

sealed class AppScreens(val route: String) {
    // Pantallas
    object ListaTareas: AppScreens("lista_tareas")
    object Tarea: AppScreens("tarea")
    object TipoTarea: AppScreens("tipo_tarea")
}