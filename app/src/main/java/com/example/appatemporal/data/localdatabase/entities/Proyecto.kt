package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyecto_table")
data class Proyecto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_proyecto") val id_proyecto: Int,
    @ColumnInfo(name = "nombre_proyecto") val nombre_proyecto: Double,
    @ColumnInfo(name = "fecha_inicio") val fecha_inicio: String,
    @ColumnInfo(name = "created_at") val created_at: String,
    @ColumnInfo(name = "modified_at") val modified_at: String
)
