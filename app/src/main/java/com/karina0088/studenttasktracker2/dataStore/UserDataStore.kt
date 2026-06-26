package com.karina0088.studenttasktracker2.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.karina0088.studenttasktracker2.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore("user")

class UserDataStore(
    private val context: Context
) {

    companion object {
        val TOKEN = stringPreferencesKey("token")
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PHOTO = stringPreferencesKey("photo")
    }

    val userFlow: Flow<User> = context.dataStore.data
        .catch {
            if (it is IOException) emit(emptyPreferences())
            else throw it
        }
        .map { pref ->
            User(
                nama = pref[NAME] ?: "",
                email = pref[EMAIL] ?: "",
                photoUrl = pref[PHOTO] ?: ""
            )
        }

    // ✅ UPDATE: tambah token (penting untuk Supabase session)
    suspend fun saveData(
        nama: String,
        email: String,
        photo: String,
        token: String? = null
    ) {
        context.dataStore.edit { pref ->
            pref[NAME] = nama
            pref[EMAIL] = email
            pref[PHOTO] = photo

            token?.let {
                pref[TOKEN] = it
            }
        }
    }

    suspend fun clearData() {
        context.dataStore.edit { it.clear() }
    }
}