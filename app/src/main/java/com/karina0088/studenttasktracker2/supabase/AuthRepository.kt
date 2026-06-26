package com.karina0088.studenttasktracker2.supabase

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.IDToken

class AuthRepository {

    // =========================
    // EMAIL PASSWORD LOGIN
    // =========================
    suspend fun login(email: String, password: String) {
        SupabaseClient.client.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    // =========================
    // GOOGLE LOGIN
    // =========================
    suspend fun loginWithGoogle(idToken: String) {
        SupabaseClient.client.auth.signInWith(IDToken) {
            this.idToken = idToken
            provider = Google
        }
    }

    // =========================
    // LOGOUT
    // =========================
    suspend fun logout() {
        SupabaseClient.client.auth.signOut()
    }

    // =========================
    // SESSION
    // =========================
    fun currentUser() =
        SupabaseClient.client.auth.currentUserOrNull()

    fun currentSession() =
        SupabaseClient.client.auth.currentSessionOrNull()
}