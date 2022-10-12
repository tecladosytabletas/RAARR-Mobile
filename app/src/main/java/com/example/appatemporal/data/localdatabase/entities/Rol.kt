package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Local database entity for role
 *
 * @param idRol: Int -> Numeric id for the role
 * @param rol: String -> Name of the role
 */
@Entity(tableName = "rol_table")
data class Rol(
    @PrimaryKey(autoGenerate = true) val idRol: Int = 0,
    @ColumnInfo(name = "rol") val rol: String
)
