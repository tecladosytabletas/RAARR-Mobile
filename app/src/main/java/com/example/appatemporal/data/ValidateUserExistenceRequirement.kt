package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class ValidateUserExistenceRequirement {
    /**
     * Invoke function that asks for the repository to
     * verify the user's existence in Firestore
     *
     * @param uid: String -> User uid
     * @param repository: Repository -> Repository of the application
     * @return Boolean -> Existence of the user
     */
    suspend operator fun invoke (uid: String, repository: Repository) : Boolean{
        return repository.verifyUser(uid)
    }
}