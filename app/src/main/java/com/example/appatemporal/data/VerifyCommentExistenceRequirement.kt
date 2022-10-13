package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class VerifyCommentExistenceRequirement {

    /**
     * Invoke function that asks for the repository to
     * verify existence of a comment on event
     *
     * @param idUser: String -> the User's id
     * @param idEvent: String -> the Event's id
     * @param repository: Repository -> Repository of the application
     * @return Boolean -> existance verified of comment
     */
    suspend operator fun invoke(idUser: String, idEvent: String, repository: Repository) : Boolean {
        return repository.verifyCommentExistence(idUser, idEvent)
    }
}