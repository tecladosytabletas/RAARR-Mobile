package com.example.appatemporal.data

import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.QuerySnapshot

class GetUserTicketRequirement {

    suspend operator fun invoke(uid: String, eid: String, fid: String, repository:Repository) : String {
        return repository.getUserTicket(uid, eid, fid).toString()

    }
}