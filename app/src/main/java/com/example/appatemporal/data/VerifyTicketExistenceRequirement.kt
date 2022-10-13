package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class VerifyTicketExistenceRequirement {
    /**
     * Invoke function that asks for the repository to
     * verify if the scanned ticket exists
     *
     * @param result: String -> Qr hash content
     * @param repository: Repository -> Repository of the application
     * @return Boolean -> True if the scanned ticket exists, false if it does not
     */
    suspend operator fun invoke(result: String, repository: Repository) : Boolean {
        return repository.verifyTicketExistence(result)
    }
}