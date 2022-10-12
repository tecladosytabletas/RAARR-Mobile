package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */
class VerifyRatingExistanceRequirement {

    /**
     * Invoke function that asks for the repository to
     * get comments from Firestore
     *
     * @param idUser: String -> the User's id
     * @param idEvent: String -> the Event's id
     * @param repository: Repository -> Repository of the application
     * @return Boolean -> existance verified of rate
     */
    suspend operator fun  invoke(idUser:String, idEvent:String,repository: Repository ):Boolean{
        return repository.verifyRatingExistence(idUser,idEvent)
    }
}