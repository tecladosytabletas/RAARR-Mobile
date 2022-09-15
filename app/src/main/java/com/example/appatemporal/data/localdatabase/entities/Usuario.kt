package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "usuario_table")
data class Usuario(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellidos") val apellido: String,
    @ColumnInfo(name = "login") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "celular") val celular: String,
    @ColumnInfo(name = "edad") val edad: Int,
    @ColumnInfo(name = "genero") val genero: String,
    @ColumnInfo(name = "fecha_creacion") val fechaCreacion: Date,
    @ColumnInfo(name = "fecha_modificacion") val fechaModificacion: Date,
    @ColumnInfo(name  = "activo") val activo: Boolean

)
