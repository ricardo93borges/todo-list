package com.example.todolist.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromStatus(value: Status): String = value.name

    @TypeConverter
    fun toStatus(value: String): Status = enumValueOf<Status>(value)
}