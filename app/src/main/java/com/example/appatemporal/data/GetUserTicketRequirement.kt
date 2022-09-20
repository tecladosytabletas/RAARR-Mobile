package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.QuerySnapshot

class GetUserTicketRequirement {

    suspend operator fun invoke(uid: String, eid: String, fid: String, repository:Repository) : String {
        val ticket = repository.getUserTicket(uid, eid, fid).documents[0].data?.get("Hash_QR").toString()
        return ticket
    }
}