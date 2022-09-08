package com.example.appatemporal.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appatemporal.data.localdatabase.entities.Objetivo

@Dao
interface ObjetivoDao {
    // Get all Objetivo
    @Query("SELECT * FROM objetivo_table")
    suspend fun getAll(): List<Objetivo>

    // Insert all Objetivo
    @Insert
    suspend fun insertAll(objetivos: List<Objetivo>)

    // Insert one Objetivo
    @Insert
    suspend fun insert(objetivo: Objetivo)

    // Get Objetivo by id
    @Query("SELECT * FROM objetivo_table WHERE id_objetivo = :id")
    suspend fun getById(id: Int): Objetivo

    // Delete all Objetivos
    @Query("DELETE FROM objetivo_table")
    suspend fun deleteAll()

    // Delete a Objetivo
    @Delete
    suspend fun delete(objetivo: Objetivo)
}