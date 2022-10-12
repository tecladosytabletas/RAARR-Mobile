package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */


class GetEventNameRequirement {
    /**
     * Invoke a function that asks the repository the name of a specific event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     * @return String -> name of the specific event
     **/
    suspend operator fun invoke(eid: String, repository: Repository) : String {
        return repository.getEventName(eid)
    }
}