package com.karina0088.studenttasktracker2.screen.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

import kotlinx.coroutines.launch

import com.karina0088.studenttasktracker2.auth.GoogleAuth
import com.karina0088.studenttasktracker2.navigation.Screen
import com.karina0088.studenttasktracker2.viewmodel.TaskViewModel
import com.karina0088.studenttasktracker2.viewmodel.AuthViewModel
import com.karina0088.studenttasktracker2.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    taskViewModel: TaskViewModel,
    authViewModel: AuthViewModel
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val credentialManager = CredentialManager.create(context)

    val request = GoogleAuth.getGoogleRequest(
        serverClientId = "145577904103-2a9npt7ccra7u6a8fdfr4mo8upjhg1n4.apps.googleusercontent.com"
    )

    val tasks by taskViewModel.allTask.collectAsState()

    var search by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf("Semua") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    val filteredTasks = tasks.filter { task ->
        val cocokSearch =
            task.title.contains(search, ignoreCase = true) ||
                    task.description.contains(search, ignoreCase = true)

        val cocokFilter = when (filter) {
            "Selesai" -> task.isDone
            "Belum" -> !task.isDone
            else -> true
        }

        cocokSearch && cocokFilter
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Student Task Tracker") },

                actions = {

                    IconButton(onClick = {
                        scope.launch {
                            try {

                                val result = credentialManager.getCredential(
                                    context,
                                    request
                                )

                                val credential = result.credential

                                val idToken = GoogleAuth.getIdToken(credential)

                                val googleIdToken =
                                    GoogleIdTokenCredential.createFrom(credential.data)

                                val name = googleIdToken.displayName ?: ""
                                val email = googleIdToken.id ?: ""
                                val photo = googleIdToken.profilePictureUri?.toString() ?: ""

                                authViewModel.loginWithGoogle(
                                    idToken, name, email, photo
                                )

                                navController.navigate(Screen.Home.route) {
                                    popUpTo(0)
                                }

                            } catch (e: Exception) {
                                Log.e("LOGIN_ERROR", "Google login crash: ", e)
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Login Google"
                        )
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.Add.route) }
            ) {
                Text("+")
            }
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = { Text("Cari tugas") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = filter == "Semua",
                    onClick = { filter = "Semua" },
                    label = { Text("Semua") }
                )

                FilterChip(
                    selected = filter == "Belum",
                    onClick = { filter = "Belum" },
                    label = { Text("Belum") }
                )

                FilterChip(
                    selected = filter == "Selesai",
                    onClick = { filter = "Selesai" },
                    label = { Text("Selesai") }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (filteredTasks.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tidak ada tugas")
                }

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(filteredTasks) { task ->
                        TaskCard(
                            task = task,
                            onDelete = { taskViewModel.delete(task) },
                            onEdit = { selectedTask = task },
                            onCheckedChange = { checked ->
                                taskViewModel.update(task.copy(isDone = checked))
                            }
                        )
                    }
                }
            }
        }
    }

    selectedTask?.let { task ->
        EditTaskDialog(
            task = task,
            onDismiss = { selectedTask = null },
            onUpdate = {
                taskViewModel.update(it)
                selectedTask = null
            }
        )
    }
}