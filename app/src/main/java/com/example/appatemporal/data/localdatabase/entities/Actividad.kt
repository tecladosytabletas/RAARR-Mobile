package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actividad_table")
data class Actividad(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_actividad") val id_actividad: Int,
    @ColumnInfo(name = "nombre_actividad") val nombre_actividad: String,
    @ColumnInfo(name = "created_at") val created_at: String,
    @ColumnInfo(name = "modified_at") val modified_at: String,
)
