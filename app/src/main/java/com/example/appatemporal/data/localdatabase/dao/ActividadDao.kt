package com.example.appatemporal.data.localdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.localdatabase.entities.Proyecto


@Dao
interface ActividadDao {

    // Get all activities
    @Query("SELECT * FROM actividad_table WHERE id_proyecto = :id")
    suspend fun getAllActivityId(id: Int): List<Actividad>

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

    //Count activities by status
    @Query("SELECT COUNT(*) FROM actividad_table WHERE id_actividad = :id_a AND id_estatus = :id_e")
    suspend fun countPendingActivities(id_a: Int, id_e: Int): Int

    //Count activities by status
    @Query("SELECT COUNT(*) FROM actividad_table WHERE id_actividad = :id_a AND id_estatus = :id_e")
    suspend fun countDoneActivities(id_a: Int, id_e: Int): Int


    // Delete an activity
    @Delete
    suspend fun delete(actividad: Actividad)

    // Update a Actividad
    @Update
    suspend fun update(actividad: Actividad)


}