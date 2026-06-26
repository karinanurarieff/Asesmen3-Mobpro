package com.karina0088.studenttasktracker2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karina0088.studenttasktracker2.screen.ui.AddTaskScreen
import com.karina0088.studenttasktracker2.screen.ui.HomeScreen
import com.karina0088.studenttasktracker2.viewmodel.AuthViewModel
import com.karina0088.studenttasktracker2.viewmodel.TaskViewModel

@Composable
fun NavGraph(
    taskViewModel: TaskViewModel,
    authViewModel: AuthViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                taskViewModel = taskViewModel,
                authViewModel = authViewModel
            )
        }

        composable(Screen.Add.route) {
            AddTaskScreen(
                navController = navController,
                viewModel = taskViewModel
            )
        }
    }
}