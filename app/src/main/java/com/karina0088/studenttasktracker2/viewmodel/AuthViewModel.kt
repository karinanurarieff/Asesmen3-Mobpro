package com.karina0088.studenttasktracker2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karina0088.studenttasktracker2.supabase.AuthRepository
import com.karina0088.studenttasktracker2.dataStore.UserDataStore
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: AuthRepository,
    private val dataStore: UserDataStore
) : ViewModel() {

    fun loginWithGoogle(
        idToken: String,
        name: String,
        email: String,
        photo: String
    ) {
        viewModelScope.launch {

            // 1. login ke Supabase
            repo.loginWithGoogle(idToken)

            // 2. simpan ke DataStore (VERSI BARU KAMU)
            dataStore.saveData(
                nama = name,
                email = email,
                photo = photo,
                token = idToken
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            repo.logout()
            dataStore.clearData()
        }
    }
}