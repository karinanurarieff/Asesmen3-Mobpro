package com.karina0088.studenttasktracker2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    val title:String,

    val description:String,

    val deadline:String,

    val status:Boolean=false,

    val imageUrl:String=""
)