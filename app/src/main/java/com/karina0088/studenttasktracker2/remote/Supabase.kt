package com.karina0088.studenttasktracker2.remote

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Supabase {

    val client = createSupabaseClient(

        supabaseUrl = "https://lvprxjczooyfhdwjntao.supabase.co/rest/v1/",

        supabaseKey = "sb_publishable_JXah_a0-dhxNbK_SecKYSg_610jIeKv"

    ) {

        install(Postgrest)

    }

}