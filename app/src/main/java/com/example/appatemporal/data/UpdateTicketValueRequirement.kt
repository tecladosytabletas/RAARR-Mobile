package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.DocumentSnapshot

/**
 * Class that invokes a function when called
 */
class UpdateTicketValueRequirement {
    /**
     * Invoke function that asks for the repository to
     * update the ticket value
     *
     * @param resulted: String -> Qr hash content
     * @param repository: Repository -> Repository of the application
     * @return Boolean -> True if the database was updated, false if it was not
     */
    suspend operator fun invoke(resulted: String, repository: Repository) : Boolean {
        return repository.updateTicketValue(resulted)
    }
}