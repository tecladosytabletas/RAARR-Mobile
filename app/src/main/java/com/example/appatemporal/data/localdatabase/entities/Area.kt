package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "area_table")
data class Area(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_area") val id_area: Int = 0,
    @ColumnInfo (name = "nombre_area") val nombre_area: String,
    @ColumnInfo (name = "created_at") val created_at: String,
    @ColumnInfo (name = "modified_at") val modified_at: String,
)
