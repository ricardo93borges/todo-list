package com.example.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Task
import com.example.todolist.data.TaskDao
import com.example.todolist.data.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {
    var tasks: LiveData<MutableList<Task>>? = null

    fun getTasks() {
        viewModelScope.launch {
            tasks = taskRepository.getAll()
        }
    }

    fun toggleTaskStatus(task: Task) {
        viewModelScope.launch {
            task.status = if(task.status == "DONE") "TODO" else "DONE"
            taskRepository.update(task)
        }
    }
}