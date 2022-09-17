package com.example.appatemporal.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appatemporal.data.localdatabase.entities.Usuario

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLocalDB(vararg usuarioDao: Usuario)

    @Query("SELECT * FROM usuario_table WHERE id = :userUid")
    suspend fun getUserLocalDB(userUid: String) : Usuario
}