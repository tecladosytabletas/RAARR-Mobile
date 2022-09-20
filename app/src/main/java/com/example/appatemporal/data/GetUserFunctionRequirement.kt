package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.QuerySnapshot

class GetUserFunctionRequirement {
    suspend operator fun invoke(uid: String, repository: Repository) : QuerySnapshot {
        return repository.getUserFunction(uid)
    }
}