package com.example.appatemporal.domain.models

import java.util.*

/**
 * Model for inserting the comment of an event to Firebase
 *
 * @param id_usuario_fk: String -> Uid of the user
 * @param id_evento_fk: String -> Uid of the event
 * @param comentario: String -> comment of the event
 * @param fecha_creacion: String -> Date of creation
 */
data class CommentModel(
    val id_usuario_fk: String,
    val id_evento_fk: String,
    val comentario: String,
    val fecha_creacion: String
)