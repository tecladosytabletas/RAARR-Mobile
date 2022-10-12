package com.example.appatemporal.data

import com.example.appatemporal.data.localdatabase.entities.Usuario
import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class AddUserLocalDBRequirement {

    /**
     * Invoke function that asks for the repository to
     * add the user information to the local database
     *
     * @param user: String -> User uid
     * @param repository: Repository -> Repository of the application
     */
    suspend operator fun invoke(user: Usuario, repository: Repository) {
        repository.addUserLocalDB(user)
    }
}