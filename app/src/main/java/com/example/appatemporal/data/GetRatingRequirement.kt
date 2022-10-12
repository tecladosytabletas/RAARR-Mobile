package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetRatingRequirement {
    /**
     * Invoke a function that asks the repository to get the average rating
     * of all the events that have been hosted by the user
     *
     * @param uid: String -> user uid
     * @param repository: Repository -> Repository of the application
     * @return Float -> average overall rating
     **/
    suspend operator fun invoke(uid: String, repository: Repository) : Float {
        return repository.getRating(uid)
    }
}
