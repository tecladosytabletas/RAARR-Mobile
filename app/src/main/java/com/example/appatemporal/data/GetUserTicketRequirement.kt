package com.example.appatemporal.data

import android.util.Log
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.TicketModel
import com.google.firebase.firestore.QuerySnapshot

class GetUserTicketRequirement {

    suspend operator fun invoke(uid: String, repository:Repository) : MutableList<TicketModel> {
        val ticket = repository.getUserTickets(uid)
        Log.d("LOG Requirement",repository.getUserTickets(uid).toString())
        return ticket
    }
}