package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository

/**
 * Class that invokes a function when called
 */

class GetTypeSATickets {
    /**
     * Invoke a function that asks the repository to get the number of tickets sold
     * and the number of real assistants to calculate out of the tickets sold how many
     * actually went to an event but segregated by ticket type to see how many of each type assisted
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     * @return MutableMap<String, Pair<Int?, Int?>>
     **/
    suspend operator fun invoke(eid: String, repository: Repository) : MutableMap<String, Pair<Int?, Int?>> {
        return repository.getTicketTypeSA(eid)
    }
}