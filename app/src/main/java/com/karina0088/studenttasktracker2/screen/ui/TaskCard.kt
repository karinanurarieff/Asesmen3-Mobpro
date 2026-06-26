package com.karina0088.studenttasktracker2.screen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.karina0088.studenttasktracker2.data.Task

@Composable
fun TaskCard(
    task: Task,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Checkbox + Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = onCheckedChange
                )

                if (task.isDone) {
                    Text(
                        text = "Selesai",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = task.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(task.description)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Deadline : ${task.deadline}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                OutlinedButton(
                    onClick = onEdit
                ) {
                    Text("Edit")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onDelete
                ) {
                    Text("Hapus")
                }

            }

        }

    }

}