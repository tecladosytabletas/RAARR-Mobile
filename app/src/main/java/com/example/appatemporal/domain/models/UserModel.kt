package com.example.appatemporal.domain.models

/**
 * Model for inserting user to Firebase
 *
 * @param nombre: String -> name of the user
 * @param apellidos: String -> last name of the user
 * @param email: String -> email of the user
 * @param fecha_nacimiento: String -> birth date of the user
 * @param genero: String -> gender of the user
 */
data class UserModel (
    val nombre: String,
    val apellidos: String,
    val email: String,
    val fecha_nacimiento: String,
    val genero: String
)
