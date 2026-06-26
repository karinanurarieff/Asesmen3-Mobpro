package com.karina0088.studenttasktracker2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karina0088.studenttasktracker2.dataStore.UserDataStore
import com.karina0088.studenttasktracker2.supabase.AuthRepository

class AuthViewModelFactory(
    private val repo: AuthRepository,
    private val dataStore: UserDataStore
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repo, dataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}