package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class addCommentRequirement {

    /**
     * Invoke function that asks for the repository to
     * add a comment in Firestore
     *
     * @param idUser: String -> the User's id
     * @param idEvent: String -> the Event's id
     * @param comment: String -> the User´s comment
     * @param repository: Repository -> Repository of the application
     */
    suspend operator fun invoke(idUser: String, idEvent : String, comment : String, repository: Repository) {
        repository.addComment(idUser, idEvent, comment)
    }
}