package com.example.appatemporal.domain.models

import java.util.*

data class EventModel (
    var id: String,
    var nombre: String,
    var descripcion: String,
    var ciudad: String,
    var estado: String,
    var ubicacion: String,
    var direccion: String,
    var longitud: Double,
    var latitud: Double,
    var imagen: String,
    var video: String,
    var activo: Int,
)