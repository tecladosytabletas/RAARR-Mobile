package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "costo_table", foreignKeys = [ForeignKey(entity = Proyecto::class, parentColumns = ["id_proyecto"], childColumns = ["id_proyecto"])])
data class Costo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_costo") val id_costo : Int,
    @ColumnInfo(name = "id_proyecto") val id_proyecto : Int,
    @ColumnInfo(name = "nombre_costo") val nombre_costo : String,
    @ColumnInfo(name = "monto") val monto : Double,
    @ColumnInfo(name = "created_at") val created_at : String,
    @ColumnInfo(name = "modified_at") val modified_at : String,
)
