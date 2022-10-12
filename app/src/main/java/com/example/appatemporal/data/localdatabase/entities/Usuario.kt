package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ROOM model for user
 * @param id: Int, id of user
 * @param nombre: String, name of user
 * @param apellidos: String, last name of user
 * @param email: String, email of user
 * @param fechaNac: String, birthdate of user
 * @param genero: String genre of user.
 * @param rol: String, role of user
 * @param area: String, area of activity.
 * @param priority: String, priority of activity
 * @param id_proyecto: String, id of project
 */
@Entity(tableName = "usuario_table")
data class Usuario(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellidos") val apellido: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "fechaNacimiento") val fechaNac: String,
    @ColumnInfo(name = "genero") val genero: String,
    @ColumnInfo(name = "rol") val rol: String,
)
