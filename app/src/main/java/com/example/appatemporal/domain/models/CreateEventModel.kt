package com.example.appatemporal.domain.models

import java.util.*

data class CreateEventModel(
    val nombre: String,
    val descripcion: String,
    val ciudad: String,
    val estado: String,
    val ubicacion: String,
    val direccion: String,
    val longitud: String,
    val latitud: String,
    val foto_portada: String,
    val video: String,
    val activo: Int,
    val aprobado: Int,
    val divisa: String,
    val fecha_creado: String,
    val fecha_modificado: String
)

