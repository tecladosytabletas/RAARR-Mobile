package com.example.appatemporal.domain.models

import java.io.FileDescriptor

/**
 * Model for inserting a failure report to Firebase
 *
 * @param titulo: String -> Title of the failure report
 * @param descripcion: String -> Description of the failure report
 */
data class ReportFailureModel(
    val titulo: String,
    val descripcion: String
)
