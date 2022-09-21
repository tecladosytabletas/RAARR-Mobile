package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetUserLocalDBRequirement
import com.example.appatemporal.data.UpdateTicketValueRequirement
import com.example.appatemporal.data.localdatabase.entities.Usuario
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class ScanQRViewModel : ViewModel() {
    val qrcode = MutableLiveData<Boolean>()
    private val updateTicketValueRequirement = UpdateTicketValueRequirement()

    fun updateTicketValue(resulted: String, repository:Repository) {
        viewModelScope.launch {
            val qrHash = updateTicketValueRequirement(resulted, repository)
            qrcode.postValue(qrHash)
        }
    }
}