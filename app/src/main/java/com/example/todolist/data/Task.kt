package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
)
