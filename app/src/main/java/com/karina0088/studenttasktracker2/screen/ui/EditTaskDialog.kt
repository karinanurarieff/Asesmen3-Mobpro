package com.karina0088.studenttasktracker2.screen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.karina0088.studenttasktracker2.data.Task

@Composable
fun EditTaskDialog(
    task: Task,
    onDismiss: () -> Unit,
    onUpdate: (Task) -> Unit
) {

    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var deadline by remember { mutableStateOf(task.deadline) }

    Dialog(onDismissRequest = onDismiss) {

        Card {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Edit Task",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Judul") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Deskripsi") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = deadline,
                    onValueChange = { deadline = it },
                    label = { Text("Deadline") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text("Batal")
                    }

                    Button(
                        onClick = {

                            onUpdate(
                                task.copy(
                                    title = title,
                                    description = description,
                                    deadline = deadline
                                )
                            )

                        }
                    ) {
                        Text("Update")
                    }

                }

            }

        }

    }

}