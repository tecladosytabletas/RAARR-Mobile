package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.DocumentSnapshot

class UpdateTicketValueRequirement {
    suspend operator fun invoke(resulted: String, repository: Repository) : String {
        return repository.updateTicketValue(resulted)
    }
}