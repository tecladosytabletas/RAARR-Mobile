package com.example.appatemporal.data.localdatabase.dao

import androidx.room.*
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.localdatabase.entities.Costo

/**
 * Interface for CostoDao
 */
@Dao
interface CostoDao {
    /**
     * Function for getting all activities of a project
     * @param id: Int project id
     * @return: List of Costo objects
     */
    @Query("SELECT * FROM costo_table where id_proyecto = :id")
    suspend fun getAll(id: Int): List<Costo>

    /**
     * Function for adding a new cost
     * @param costo: Costo object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(costo: Costo)

    /**
     * Function for getting one cost by id
     * @param id: integer id of cost
     * @return: Costo object
     */
    @Query("SELECT * FROM costo_table WHERE id_costo = :id")
    suspend fun getById(id: Int): Costo

    /**
     * Function for deleting a cost
     * @param costo: Costo object
     */
    @Delete
    suspend fun delete(costo: Costo)

    /**
     * Function for updating a cost
     * @param costo: Costo object
     */
    @Update
    suspend fun update(costo: Costo)

}