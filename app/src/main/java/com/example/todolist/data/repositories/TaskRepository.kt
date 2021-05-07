package com.example.todolist.data.repositories

import androidx.lifecycle.LiveData
import com.example.todolist.data.Status
import com.example.todolist.data.Task
import com.example.todolist.data.TaskDao
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    fun getAll(): LiveData<MutableList<Task>> {
        return taskDao.getAll()
    }

    fun getToDoTasks(): LiveData<MutableList<Task>> {
        return taskDao.getByStatus(Status.TODO)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }
}