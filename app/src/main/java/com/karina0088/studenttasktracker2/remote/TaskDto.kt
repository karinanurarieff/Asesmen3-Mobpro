package com.karina0088.studenttasktracker2.remote

import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(

    val id: String? = null,

    val title: String,

    val description: String,

    val deadline: String,

    val is_done: Boolean

)