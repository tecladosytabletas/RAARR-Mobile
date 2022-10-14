package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class AddRatingRequirement {

    /**
     * Invoke function that asks for the repository to
     * add rating in Firestore
     *
     * @param idUser: String -> the User's id
     * @param idEvent: String -> the Event's id
     * @param rate: Float -> the Event's rate
     * @param repository: Repository -> Repository of the application
     */
    suspend operator fun invoke(idUser: String, idEvent : String, rate : Float, repository: Repository) {
        repository.addRating(idUser, idEvent, rate)
    }


}