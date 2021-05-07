package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Status {
    TODO,
    DONE
}

@Entity
data class Task(
    @PrimaryKey val id: Int,
    val name: String,
    var status: Status,
)
