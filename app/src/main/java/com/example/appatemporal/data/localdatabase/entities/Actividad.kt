package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * ROOM model for activity
 * @param id_actividad: Int, id of activity
 * @param id_estatus: Int, id of status.
 * @param  nombre_actividad: String, name of activity
 * @param area: String, area of activity.
 * @param priority: String, priority of activity
 * @param id_proyecto: String, id of project
 */
@Entity(tableName = "actividad_table")
data class Actividad(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_actividad") val id_actividad: Int,
    @ColumnInfo(name = "id_estatus") val id_estatus: Int,
    @ColumnInfo(name = "nombre_actividad") var nombre_actividad: String,
    @ColumnInfo(name = "area") var area: String,
    @ColumnInfo(name = "estatus") var estatus: String,
    @ColumnInfo(name = "prioridad") var prioridad: String,
    @ColumnInfo(name = "id_proyecto") val id_proyecto: Int,
)

