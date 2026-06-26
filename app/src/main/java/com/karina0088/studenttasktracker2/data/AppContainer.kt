package com.karina0088.studenttasktracker2.data

import android.content.Context
import androidx.room.Room

class AppContainer(context: Context) {

    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "task_db"
    ).build()

    val repository = TaskRepository(database.taskDao())

}