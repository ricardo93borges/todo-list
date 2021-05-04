package com.example.todolist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<MutableList<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun get(id: Int): Task

    @Insert
    fun insert(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Task>)

    @Update
    suspend fun update(task: Task)

    @Delete
    fun delete(task: Task)
}