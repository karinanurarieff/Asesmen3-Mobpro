package com.karina0088.studenttasktracker2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karina0088.studenttasktracker2.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}