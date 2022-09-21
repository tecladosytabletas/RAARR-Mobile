package com.example.appatemporal.framework.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetUserTicketRequirement
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.TicketModel
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch

class GetUserTicketViewModel : ViewModel() {

    private var getUserTicketRequirement = GetUserTicketRequirement()

    val ticket = MutableLiveData<MutableList<TicketModel>>()

    fun getUserTicket(uid: String, repository: Repository){
        viewModelScope.launch {
            val ticketAux = getUserTicketRequirement(uid, repository)
            Log.d("LOG ViewModel", getUserTicketRequirement(uid, repository).toString())
            ticket.postValue(ticketAux)
        }
    }
}