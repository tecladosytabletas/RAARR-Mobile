package com.example.appatemporal.domain.models

data class UserModel (
    val nombre: String,
    val apellidos: String,
    val email: String,
    val fecha_nacimiento: String,
    val genero: String
)
