package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.QuerySnapshot

/**
 * Class that invokes a function when called
 */
class GetCommentsRequirement {
    /**
     * Invoke function that asks for the repository to
     * get comments from Firestore
     *
     * @param idEvent: String -> Event's id
     * @param repository: Repository -> Repository of the application
     * @return QuerySnapshot -> comments from repository
     */
    suspend operator fun invoke(idEvent: String, repository: Repository) : QuerySnapshot {
        return repository.getComments(idEvent)
    }
}