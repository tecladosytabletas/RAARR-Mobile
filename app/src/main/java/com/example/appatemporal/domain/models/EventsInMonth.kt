package com.example.appatemporal.domain.models

import java.util.*

data class EventsInMonth(
    val idEvent: String,
    val nombreEvento: String,
    val lugarEvento: String,
    val descEvento: String
)