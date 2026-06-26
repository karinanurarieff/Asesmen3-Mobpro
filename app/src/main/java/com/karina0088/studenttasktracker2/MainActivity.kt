package com.karina0088.studenttasktracker2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider

import com.karina0088.studenttasktracker2.data.DatabaseProvider
import com.karina0088.studenttasktracker2.data.TaskRepository

import com.karina0088.studenttasktracker2.dataStore.UserDataStore
import com.karina0088.studenttasktracker2.supabase.AuthRepository

import com.karina0088.studenttasktracker2.navigation.NavGraph
import com.karina0088.studenttasktracker2.ui.theme.StudentTaskTracker2Theme

import com.karina0088.studenttasktracker2.viewmodel.TaskViewModel
import com.karina0088.studenttasktracker2.viewmodel.TaskViewModelFactory
import com.karina0088.studenttasktracker2.viewmodel.AuthViewModel
import com.karina0088.studenttasktracker2.viewmodel.AuthViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // =========================
        // DATABASE + TASK
        // =========================
        val dao = DatabaseProvider.getDatabase(this).taskDao()
        val taskRepository = TaskRepository(dao)

        // =========================
        // AUTH
        // =========================
        val authRepository = AuthRepository()
        val userDataStore = UserDataStore(this)

        setContent {

            StudentTaskTracker2Theme {

                val taskViewModel = ViewModelProvider(
                    this,
                    TaskViewModelFactory(taskRepository)
                )[TaskViewModel::class.java]

                val authViewModel = ViewModelProvider(
                    this,
                    AuthViewModelFactory(authRepository, userDataStore)
                )[AuthViewModel::class.java]

                NavGraph(
                    taskViewModel = taskViewModel,
                    authViewModel = authViewModel
                )
            }
        }
    }
}