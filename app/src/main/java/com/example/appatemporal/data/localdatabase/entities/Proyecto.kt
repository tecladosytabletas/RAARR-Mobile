package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyecto_table")
data class Proyecto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_proyecto") val id_proyecto: Int,
    @ColumnInfo(name = "id_usuario") val id_usuario: Int,
    @ColumnInfo(name = "nombre_proyecto") val nombre_proyecto: String,
    @ColumnInfo(name = "fecha_inicio") val fecha_inicio: String
)
