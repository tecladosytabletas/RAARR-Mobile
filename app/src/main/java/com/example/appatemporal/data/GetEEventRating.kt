package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetEEventRating {
    /**
     * Invoke a function that asks the repository to get the sum of all ratings,
     * the number of times a certain rating appears and the general rating of a specific
     * event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     * @return MutableList<Float>
     **/
    suspend operator fun invoke(eid: String, repository: Repository) : MutableList<Float> {
        return repository.getRatingByEvent(eid)
    }
}