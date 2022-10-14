package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetVentasCountRequirement {
    /**
     * Invoke a function that asks the repository to get the number of sold tickets
     * and tickets marked as assisted in order to compare both (expected vs real assists) from
     * all of the tickets a user has sold
     *
     * @param uid: String -> user uid
     * @param repository: Repository -> Repository of the application
     * @return Pair<Int, Int>
     **/
    suspend operator fun invoke(uid: String, repository: Repository) : Pair<Int, Int> {
        return repository.ventasCount(uid)
    }
}