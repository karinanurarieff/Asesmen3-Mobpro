package com.karina0088.studenttasktracker2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karina0088.studenttasktracker2.data.Task
import com.karina0088.studenttasktracker2.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.karina0088.studenttasktracker2.supabase.SupabaseRepository
import com.karina0088.studenttasktracker2.data.SupabaseTask

class TaskViewModel(
    private val repository: TaskRepository

) : ViewModel() {

    private val supabaseRepository = SupabaseRepository()
    val allTask = repository.allTask
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun insert(task: Task) {

        viewModelScope.launch {

            repository.insert(task)

            supabaseRepository.insertTask(

                SupabaseTask(
                    title = task.title,
                    description = task.description,
                    deadline = task.deadline,
                    isdone = task.isDone
                )

            )

        }

    }

    fun update(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}