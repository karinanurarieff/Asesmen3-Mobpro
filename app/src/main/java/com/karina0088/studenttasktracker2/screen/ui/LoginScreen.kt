package com.karina0088.studenttasktracker2.screen.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*

import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

import kotlinx.coroutines.launch

import com.karina0088.studenttasktracker2.auth.GoogleAuth
import com.karina0088.studenttasktracker2.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    context: Context,
    viewModel: AuthViewModel,
    serverClientId: String
) {

    val scope = rememberCoroutineScope()
    val credentialManager = CredentialManager.create(context)

    val request = GoogleAuth.getGoogleRequest(serverClientId)

    Button(onClick = {
        scope.launch {

            val result = credentialManager.getCredential(
                context,
                request
            )

            val credential = result.credential

            // 🔥 ambil ID Token Google
            val idToken = GoogleAuth.getIdToken(credential)

            // 🔥 ambil data user Google
            val googleIdToken = GoogleIdTokenCredential.createFrom(credential.data)

            val name = googleIdToken.displayName ?: ""
            val email = googleIdToken.id ?: ""
            val photo = googleIdToken.profilePictureUri?.toString() ?: ""

            // kirim ke ViewModel
            viewModel.loginWithGoogle(
                idToken = idToken,
                name = name,
                email = email,
                photo = photo
            )
        }
    }) {
        Text("Login Google")
    }
}