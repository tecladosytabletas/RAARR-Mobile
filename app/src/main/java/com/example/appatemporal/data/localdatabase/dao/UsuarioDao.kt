package com.example.appatemporal.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appatemporal.data.localdatabase.entities.Usuario


@Dao
interface UsuarioDao {
    /**
     * Insert a user to local database
     *
     * @param usuario: Usuario -> A model of Usuario local database entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLocalDB(vararg usuario: Usuario)

    /**
     * Get user information from local database
     *
     * @param userUid: String -> The uid of the user
     */
    @Query("SELECT * FROM usuario_table WHERE id = :userUid")
    suspend fun getUserLocalDB(userUid: String) : Usuario

    /**
     * Update user information of the local database
     *
     * @param userUid: String -> The uid of the user
     * @param rol: String -> The role of the user
     */
    @Query("UPDATE usuario_table SET rol = :rol WHERE id = :userUid")
    suspend fun updateUserRole(userUid: String, rol: String)
}