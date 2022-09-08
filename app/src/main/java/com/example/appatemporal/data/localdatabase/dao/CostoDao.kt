package com.example.appatemporal.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appatemporal.data.localdatabase.entities.Costo

@Dao
interface CostoDao {
    // Get all costos
    @Query("SELECT * FROM costo_table")
    suspend fun getAll(): List<Costo>

    // Insert all areas
    @Insert
    suspend fun insertAll(areas: List<Costo>)

    // Insert one area
    @Insert
    suspend fun insert(area: Costo)

    // Get area by id
    @Query("SELECT * FROM costo_table WHERE id_costo = :id")
    suspend fun getById(id: Int): Costo

    // Delete all areas
    @Query("DELETE FROM costo_table")
    suspend fun deleteAll()

    // Delete an area
    @Delete
    suspend fun delete(area: Costo)
}