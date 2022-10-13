package com.example.appatemporal.domain.models

import java.util.*

/**
 * Model for inserting the rating of an event to Firebase
 *
 * @param id_usuario_fk: String -> Uid of the user
 * @param id_evento_fk: String -> Uid of the event
 * @param rating: String -> Rating value of the event
 * @param fecha_creacion: String -> Date of creation
 */
data class RatingModel(
    val id_usuario_fk: String,
    val id_evento_fk: String,
    val rating: Float,
    val fecha_creacion: String

)
