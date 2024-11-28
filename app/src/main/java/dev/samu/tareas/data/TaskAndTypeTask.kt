package dev.samu.tareas.data

import androidx.room.Embedded
import androidx.room.Relation

data class TaskAndTypeTask(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "typeTaskId",
        entityColumn = "id"
    )
    val typeTask: TypeTask
)