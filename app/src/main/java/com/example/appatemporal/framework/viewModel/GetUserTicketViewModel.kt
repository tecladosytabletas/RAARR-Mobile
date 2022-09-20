package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetUserTicketRequirement
import com.example.appatemporal.domain.Repository
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch

class GetUserTicketViewModel : ViewModel() {

    private var getUserTicketRequirement = GetUserTicketRequirement()

    val ticket = MutableLiveData<String>()

    fun getUserTicket(uid: String, eid: String, fid: String, repository: Repository){
        viewModelScope.launch {
            val ticketAux = getUserTicketRequirement(uid, eid, fid, repository)
            ticket.postValue(ticketAux)
        }
    }
}