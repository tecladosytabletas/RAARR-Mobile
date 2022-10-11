package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model for inserting user to local database
 *
 * @param id: String -> uid of the user
 * @param nombre: String -> name of the user
 * @param apellidos: String -> last name of the user
 * @param email: String -> email of the user
 * @param fechaNacimiento: String -> birth date of the user
 * @param genero: String -> gender of the user
 * @param rol: String -> role of the user
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
