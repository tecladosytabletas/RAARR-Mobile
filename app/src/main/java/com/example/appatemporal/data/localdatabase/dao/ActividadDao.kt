package com.example.appatemporal.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appatemporal.data.localdatabase.entities.Actividad

@Dao
interface ActividadDao {

    // Get all activities
    @Query("SELECT * FROM actividad_table")
    suspend fun getAll(): List<Actividad>

    // Insert all activities
    @Insert
    suspend fun insertAll(activities: List<Actividad>)

    // Insert one activity
    @Insert
    suspend fun insert(actividad: Actividad)

    // Get activity by id
    @Query("SELECT * FROM actividad_table WHERE id_actividad = :id")
    suspend fun getById(id: Int): Actividad

    // Delete all activities
    @Query("DELETE FROM actividad_table")
    suspend fun deleteAll()

    // Delete an activity
    @Delete
    suspend fun delete(actividad: Actividad)

}