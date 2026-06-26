package com.karina0088.studenttasktracker2.data

import kotlinx.serialization.Serializable

@Serializable
data class SupabaseTask(

    val title: String,
    val description: String,
    val deadline: String,
    val isdone: Boolean

)