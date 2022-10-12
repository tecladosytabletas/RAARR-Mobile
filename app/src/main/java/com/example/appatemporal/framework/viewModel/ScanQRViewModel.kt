package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetUserLocalDBRequirement
import com.example.appatemporal.data.UpdateTicketValueRequirement
import com.example.appatemporal.data.VerifyRatingExistanceRequirement
import com.example.appatemporal.data.VerifyTicketExistenceRequirement
import com.example.appatemporal.data.localdatabase.entities.Usuario
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

/**
 * Class that inherits from ViewModel
 */
class ScanQRViewModel : ViewModel() {
    private val updateTicketValueRequirement = UpdateTicketValueRequirement()
    private val verifyTicketExistenceRequirement = VerifyTicketExistenceRequirement()

    /**
     * Verifies if the scanned tickets exists
     *
     * @param resulted: String -> Qr hash content
     * @param repository: Repository -> Repository of the application
     * @param result: Boolean -> Value from the database, true if the ticket exists, false if
     * it does not
     */
    fun verifyTicketExistence(resulted: String, repository: Repository, result: (Boolean) -> Unit) {
        viewModelScope.launch {
            val existence = verifyTicketExistenceRequirement(resulted, repository)
            result(existence)
        }
    }

    /**
     * Updates ticket value in database
     *
     * @param resulted: String -> Qr hash content
     * @param repository: Repository -> Repository of the application
     * @param result: Boolean -> Value from the database, true if the ticket was updated, false if
     * it does not
     */
    fun updateTicketValue(resulted: String, repository:Repository, result: (Boolean) -> Unit) {
        viewModelScope.launch {
            val qrHash = updateTicketValueRequirement(resulted, repository)
            result(qrHash)
            //qrcode.postValue(qrHash)
        }
    }
}