package com.karina0088.studenttasktracker2.screen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.karina0088.studenttasktracker2.data.Task
import com.karina0088.studenttasktracker2.viewmodel.TaskViewModel
import android.app.DatePickerDialog
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    val datePicker = DatePickerDialog(
        context,
        { _, year, month, day ->

            deadline = "$day/${month + 1}/$year"

        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Tambah Task",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                datePicker.show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            if (deadline.isEmpty()) {

                Text("Pilih Deadline")

            } else {

                Text(deadline)

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                viewModel.insert(

                    Task(
                        title = title,
                        description = description,
                        deadline = deadline
                    )

                )

                navController.popBackStack()

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Simpan")

        }

    }

}