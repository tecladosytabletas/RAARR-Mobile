package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetPMbyTicketsRequirement {
    /**
     * Invoke a function that asks the repository to get the number of tickets sold
     * segregated by payment method
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     * @return MutableMap<String, Int?>
     **/
    suspend operator fun invoke(eid: String, repository: Repository) : MutableMap<String, Int?> {
        return repository.getTicketsbyPM(eid)
    }
}