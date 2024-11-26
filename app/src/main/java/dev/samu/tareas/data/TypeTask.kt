package dev.samu.tareas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_tasks")
data class TypeTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)