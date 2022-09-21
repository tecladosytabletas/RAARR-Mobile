package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.TicketModel
import com.google.firebase.firestore.QuerySnapshot

class GetUserTicketRequirement {

    suspend operator fun invoke(uid: String, repository:Repository) : MutableList<TicketModel> {
        val ticket = repository.getUserTickets(uid)
        return ticket
    }
}