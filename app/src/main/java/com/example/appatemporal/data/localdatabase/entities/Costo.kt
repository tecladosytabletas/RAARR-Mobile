package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
/**
 * ROOM model for cost
 * @param id_costo: Int, id of cost
 * @param  nombre_costo: String, name of cost
 * @param monto: Int, amount of costo
 * @param id_proyecto: String, id of project
 */
@Entity(tableName = "costo_table")
data class Costo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_costo") val id_costo : Int,
    @ColumnInfo(name = "nombre_costo") var nombre_costo : String,
    @ColumnInfo(name = "monto") var monto : Int,
    @ColumnInfo(name = "id_proyecto") val id_proyecto : Int,
)
