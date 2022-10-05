package com.example.appatemporal.domain.models

data class EventsInMonth(
    val idEvent: String,
    val nombreEvento: String,
    val lugarEvento: String,
    val horaInicioEvento: String,
    val urlImagen: String
)