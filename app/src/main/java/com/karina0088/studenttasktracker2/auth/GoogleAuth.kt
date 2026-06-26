package com.karina0088.studenttasktracker2.auth

import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import androidx.credentials.Credential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

object GoogleAuth {

    fun getGoogleRequest(serverClientId: String): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(true)
                    .setServerClientId("145577904103-2a9npt7ccra7u6a8fdfr4mo8upjhg1n4.apps.googleusercontent.com")
                    .build()
            )
            .build()
    }

    fun getIdToken(credential: Credential): String {
        val googleCred = GoogleIdTokenCredential.createFrom(credential.data)
        return googleCred.idToken
    }
}