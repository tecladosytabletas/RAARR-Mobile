package com.example.appatemporal.domain.models

import java.time.LocalDateTime
import java.util.*

/**
 * Model for inserting ticket as a register sale to Firebase
 *
 * @param activo: String -> Status of the ticket
 * @param id_usuario_fk: String -> Uid of the user
 * @param id_funcion_fk: String -> Uid of the event function
 * @param id_metodo_pago_fk: String -> Uid of the payment method
 * @param id_tipo_boleto_fk: String -> Uid of the ticket type
 * @param fecha_compra: String ->  Date of purchase
 * @param fecha_asistencia: String -> Date of assistance
 * @param hash_qr: String -> Hash of the qr code
 * @param codigo_qr: string -> QR code
 */
data class TicketModel(
    val activo : Boolean,
    val id_usuario_fk: String,
    val id_funcion_fk: String,
    val id_metodo_pago_fk: String,
    val id_tipo_boleto_fk:String,
    val fecha_compra: String,
    val fecha_asistencia: String,
    val hash_qr: String,
    val codigo_qr: String
    )
