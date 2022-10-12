package com.example.appatemporal.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appatemporal.data.localdatabase.entities.Rol

@Dao
interface RolDao {
    /**
     * Adds a role to the local database
     *
     * @param rol: Rol -> A rol's entity
     */
    @Insert
    suspend fun insertRole(vararg rol: Rol)

    /**
     * Gets the information of the role
     *
     * @param idRol: Int -> Numeric id of the role
     * @return Rol -> The information of the role
     */
    @Query("SELECT * FROM rol_table WHERE idRol = :idRol")
    suspend fun getRol(idRol: Int) : Rol

    /**
     * Gets the information for all the existing roles
     *
     * @param idRol: Int -> Numeric id of the role
     * @return List<Rol> -> A list of all the roles
     */
    @Query("SELECT * FROM rol_table")
    suspend fun getAllRoles() : List<Rol>

    /**
     * Updates the information of a role
     *
     * @param idRol: Int -> numeric id of the role
     * @param rol: String -> name of the new role name
     */
    @Query("UPDATE rol_table SET rol = :rol WHERE idRol = :idRol")
    suspend fun updateRol(idRol: Int, rol: String)

    /**
     * Deletes the information of a role
     *
     * @param idRol: Int -> numeric id of the role
     */
    @Query("DELETE FROM rol_table WHERE idRol = :idRol")
    suspend fun deleteRol(idRol: Int)
}