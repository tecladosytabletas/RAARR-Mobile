package com.example.appatemporal.domain.models

data class TicketModel(
    val nombre_evento: String,
    //val foto_evento: String,
    val fecha: String,
    val horario: String,
    val lugar: String,
    val direccion: String,
    val ciudad: String,
    val estado: String,
    val hash_qr: String
)
