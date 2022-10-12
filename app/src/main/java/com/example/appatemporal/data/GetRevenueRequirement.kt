package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetRevenueRequirement {
    /**
     * Invoke a function that asks the repository to get the sum of all
     * revenues of events belonging to a user
     *
     * @param uid: String -> user uid
     * @param repository: Repository -> Repository of the application
     * @return Int -> the revenue value
     **/
    suspend operator fun invoke(uid: String, repository: Repository) : Int {
        return repository.getRevenue(uid)
    }
}