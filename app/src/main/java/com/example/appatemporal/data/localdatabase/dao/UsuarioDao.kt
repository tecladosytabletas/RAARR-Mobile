package com.example.appatemporal.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appatemporal.data.localdatabase.entities.Usuario

/**
 * Interface for Usuario Dao
 */
@Dao
interface UsuarioDao {

    /**
     * Function for adding user to local database
     * @param usuario: Usuario object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLocalDB(vararg usuario: Usuario)

    /**
     * Function for getting user from local database
     * @param userUid: String, id of user.
     * @return: Usuario object
     */
    @Query("SELECT * FROM usuario_table WHERE id = :userUid")
    suspend fun getUserLocalDB(userUid: String) : Usuario

    /**
     * Function for updating user role in local database.
     * @param userUid: String, id of user.
     * @param rol: String, role of the user.
     */
    @Query("UPDATE usuario_table SET rol = :rol WHERE id = :userUid")
    suspend fun updateUserRole(userUid: String, rol: String)
}