package com.example.appatemporal.data.localdatabase.dao

import androidx.room.*
import com.example.appatemporal.data.localdatabase.entities.Proyecto

@Dao
interface ProyectoDao {
    // Get all Proyecto
    @Query("SELECT * FROM proyecto_table")
    suspend fun getAll(): List<Proyecto>

    // Insert all Proyecto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(proyectos: List<Proyecto>)

    // Insert one Proyecto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(proyecto: Proyecto)

    // Get Proyecto by id
    @Query("SELECT * FROM proyecto_table WHERE id_proyecto = :id")
    suspend fun getById(id: Int): Proyecto

    // Delete all Proyectos
    @Query("DELETE FROM proyecto_table")
    suspend fun deleteAll()

    // Delete a Proyecto
    @Delete
    suspend fun delete(proyecto: Proyecto)

    // Update a Proyecto
    @Update
    suspend fun update(proyecto: Proyecto)
}