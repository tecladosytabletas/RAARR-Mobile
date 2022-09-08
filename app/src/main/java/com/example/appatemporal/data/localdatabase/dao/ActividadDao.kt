package com.example.appatemporal.data.localdatabase.dao

import androidx.room.*
import com.example.appatemporal.data.localdatabase.entities.Actividad

@Dao
interface ActividadDao {

    // Get all activities
    @Query("SELECT * FROM actividad_table")
    suspend fun getAll(): List<Actividad>

    // Insert all activities
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(activities: List<Actividad>)

    // Insert one activity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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