package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class ReportFailureRequirement {
    /**
     * Invoke function that asks for the repository to
     * add the failure reported in Firestore
     *
     * @param title: String -> the report's tittle
     * @param description -> the failure's description
     * @param repository: Repository -> Repository of the application
     */
    suspend operator fun invoke(title: String, description: String, repository: Repository) {
        repository.addFailure(title, description)
    }
}