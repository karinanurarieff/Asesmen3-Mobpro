package com.karina0088.studenttasktracker2.supabase

import com.karina0088.studenttasktracker2.data.SupabaseTask
import io.github.jan.supabase.postgrest.from

class SupabaseRepository {

    suspend fun insertTask(task: SupabaseTask) {

        SupabaseClient.client
            .from("tasks")
            .insert(task)

    }

}