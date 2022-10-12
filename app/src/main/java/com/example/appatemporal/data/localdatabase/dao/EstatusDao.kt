package com.example.appatemporal.data.localdatabase.dao

import androidx.room.*
import com.example.appatemporal.data.localdatabase.entities.Estatus

@Dao
interface EstatusDao {

    /**
     * Function for adding one status
     * @param estatus: Estatus object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estatus: Estatus)

}