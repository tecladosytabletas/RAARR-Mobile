package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.DocumentSnapshot

/**
 * Class that invokes a function when called
 */

class GetEventCountRequirement {
    /**
     * Invoke a function that asks the repository how many events has a
     * specific user created
     *
     * @param uid: String -> user uid
     * @param repository: Repository -> Repository of the application
     * @return Int -> number of events a user has created
     **/
    suspend operator fun invoke(uid: String, repository: Repository) : Int {
        return repository.eventCount(uid)
    }
}