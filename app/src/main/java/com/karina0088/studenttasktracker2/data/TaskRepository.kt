package com.karina0088.studenttasktracker2.data

class TaskRepository(
    private val dao: TaskDao
) {

    val allTask = dao.getAllTask()

    suspend fun insert(task: Task) {
        dao.insert(task)
    }

    suspend fun update(task: Task) {
        dao.update(task)
    }

    suspend fun delete(task: Task) {
        dao.delete(task)
    }
}