package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ROOM model for status
 * @param id_estatus: Int, id of status
 * @param  nombre_estatus: String, name of status
 * @param created_at: String, date of creation
 * @param modified_at: String, date of update
 */
@Entity(tableName = "estatus_table")
data class Estatus(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_estatus") val id_estatus: Int,
    @ColumnInfo(name = "nombre_estatus") val nombre_estatus: String,
    @ColumnInfo(name = "created_at") val created_at: String,
    @ColumnInfo(name = "modified_at") val modified_at: String
)
