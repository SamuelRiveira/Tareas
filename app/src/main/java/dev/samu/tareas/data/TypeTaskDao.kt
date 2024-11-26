package dev.samu.tareas.data

import androidx.room.*

@Dao
interface TypeTaskDao {
    @Insert
    suspend fun insert(typeTask: TypeTask)

    @Query("SELECT * FROM type_tasks")
    suspend fun getAllTypeTasks(): List<TypeTask>

    @Update
    suspend fun update(typeTask: TypeTask)

    @Delete
    suspend fun delete(typeTask: TypeTask)
}