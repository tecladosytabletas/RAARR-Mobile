package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetRevenuebyPM {
    /**
     * Invoke a function that asks the repository to get the revenue by
     * payment method of a specific event according to its id
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     * @return MutableMap<String, Int?>
     **/
    suspend operator fun invoke(eid: String, repository: Repository) : MutableMap<String, Int?> {
        return repository.getRevenuebyPM(eid)
    }
}