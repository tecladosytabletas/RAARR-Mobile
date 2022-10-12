package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ROOM model for project
 * @param id_proyecto: Int, id of project
 * @param id_usuario: Int, id of user
 * @param  nombre_proyecto: String, name of project
 * @param fecha_inicio: String, date of project
 * @param ganancia: Double, margin
 * @param presupuesto: Double, budget of project
 * @param meta: Double, goal of project
 * @param estatus_completado: Boolean for indicating if project is completed
 * @param created_at: String, Date of creation.
 */
@Entity(tableName = "proyecto_table")
data class Proyecto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_proyecto") val id_proyecto: Int,
    @ColumnInfo(name = "id_usuario") val id_usuario: Int,
    @ColumnInfo(name = "nombre_proyecto") var nombre_proyecto: String,
    @ColumnInfo(name = "fecha_inicio") var fecha_inicio: String,
    @ColumnInfo(name = "ganancia") val ganancia: Double,
    @ColumnInfo(name = "presupuesto") val presupuesto: Double,
    @ColumnInfo(name = "meta") val meta: Double,
    @ColumnInfo(name = "estatus_completado") val estatus_completado: Boolean,
    @ColumnInfo(name = "created_at") val created_at: String
)
