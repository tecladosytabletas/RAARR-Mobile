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

    // Insert all costos
    @Insert
    suspend fun insertAll(costos: List<Costo>)

    // Insert one costo
    @Insert
    suspend fun insert(costo: Costo)

    // Get costo by id
    @Query("SELECT * FROM costo_table WHERE id_costo = :id")
    suspend fun getById(id: Int): Costo

    // Delete all costos
    @Query("DELETE FROM costo_table")
    suspend fun deleteAll()

    // Delete a costo
    @Delete
    suspend fun delete(cosoto: Costo)

}