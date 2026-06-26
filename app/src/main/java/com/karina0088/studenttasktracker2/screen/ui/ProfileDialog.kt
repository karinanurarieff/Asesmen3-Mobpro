package com.karina0088.studenttasktracker2.screen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileDialog(
    name: String,
    email: String,
    photo: String? = null,
    onLogout: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Profile") },
        text = {
            Column {
                Text("Name: $name")
                Text("Email: $email")
            }
        },
        confirmButton = {
            Button(onClick = onLogout) {
                Text("Logout")
            }
        }
    )
}