package com.karina0088.studenttasktracker2.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {

    val client = createSupabaseClient(
        supabaseUrl = "https://lvprxjczooyfhdwjntao.supabase.co",
        supabaseKey = "sb_publishable_JXah_a0-dhxNbK_SecKYSg_610jIeKv"
    ) {

        install(Auth)
        install(Postgrest)
        install(Storage)
    }

}