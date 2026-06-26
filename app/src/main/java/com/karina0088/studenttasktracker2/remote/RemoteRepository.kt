package com.karina0088.studenttasktracker2.remote

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class RemoteRepository {

    suspend fun getTasks(): List<TaskDto> {

        return Supabase.client
            .from("tasks")
            .select()
            .decodeList()

    }

    suspend fun insertTask(task: TaskDto) {

        Supabase.client
            .from("tasks")
            .insert(task)

    }

}