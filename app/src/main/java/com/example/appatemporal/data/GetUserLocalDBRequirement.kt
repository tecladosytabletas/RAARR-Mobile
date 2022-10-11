package com.example.appatemporal.data

import com.example.appatemporal.data.localdatabase.entities.Usuario
import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class GetUserLocalDBRequirement {
    /**
     * Invoke function that asks for the repository to
     * get the user information to the local database
     *
     * @param user: String -> User uid
     * @param repository: Repository -> Repository of the application
     * @return Usuario: Usuario -> Local database for the user entity
     */
    suspend operator fun invoke(userUid: String, repository: Repository) : Usuario {
        return repository.getUserLocalDB(userUid)
    }
}