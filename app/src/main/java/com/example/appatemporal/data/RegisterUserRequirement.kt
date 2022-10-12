package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.UserModel

/**
 * Class that invokes a function when called
 */
class RegisterUserRequirement {
    /**
     * Invoke function that asks for the repository to
     * verify the user's existence in Firestore
     *
     * @param uid: String -> User Uid
     * @param user: UserModel -> Model to insert documents to Firestore
     * @param role: String -> Role selected by the user
     * @param repository: Repository -> Repository of the application
     */
    suspend operator fun invoke(uid: String, user: UserModel, role: String, repository: Repository) {
        repository.addUser(uid, user, role)
    }
}