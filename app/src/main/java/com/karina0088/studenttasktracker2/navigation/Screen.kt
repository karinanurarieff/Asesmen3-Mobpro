package com.karina0088.studenttasktracker2.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")

    object Home : Screen("home")

    object Add : Screen("add")

    object Profile : Screen("profile")
}