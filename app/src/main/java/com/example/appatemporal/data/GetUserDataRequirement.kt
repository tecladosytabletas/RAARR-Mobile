package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.DocumentSnapshot

/**
 * Class that invokes a function when called
 */
class GetUserDataRequirement {
    /**
     * Invoke function that asks for the repository to
     * get the user information
     *
     * @param uid: String -> User uid
     * @param repository: Repository -> Repository of the application
     * @return DocumentSnapshot -> query result from Firestore
     */
    suspend operator fun invoke(uid: String, repository: Repository) : DocumentSnapshot {
        return repository.getUser(uid)
    }
}