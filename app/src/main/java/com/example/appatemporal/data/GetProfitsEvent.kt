package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetProfitsEvent {
    /**
     * Invoke a function that asks the repository to get the profits of a
     * specific event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     * @return Int -> profits of a specific event
     **/
    suspend operator fun invoke(eid: String, repository: Repository) : Int {
        return repository.generalProfitsEvent(eid)
    }
}