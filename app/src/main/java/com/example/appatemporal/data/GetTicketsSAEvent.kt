package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetTicketsSAEvent {
    /**
     * Invoke a function that asks the repository to get the number of tickets sold
     * and the number of real assistants to calculate out of the tickets sold how many
     * actually went to an event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     * @return Pair<Int,Int>
     **/
    suspend operator fun invoke(eid: String, repository: Repository) : Pair<Int,Int> {
        return repository.getEventTicketsSA(eid)
    }
}