package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetCurrentTicketsFunRequirement
import com.example.appatemporal.data.GetDropDownNamesRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class RegisterSaleViewModel : ViewModel() {
    private val getDropDownNamesRequirement = GetDropDownNamesRequirement()
    private val getCurrentTicketsFunRequirement = GetCurrentTicketsFunRequirement()

    val dropdownList = MutableLiveData<ArrayList<Triple<String, Int, String>>>()
    val ticketAvailability = MutableLiveData<Pair<Int, Int>>()

    fun getDropdownNames(idEvent: String, repository: Repository) {
        viewModelScope.launch {
            val dropdownValues = getDropDownNamesRequirement(idEvent, repository)
            val dropdownArray = arrayListOf<Triple<String, Int, String>>()
            for (list in dropdownValues) {
                dropdownArray.add(list)
            }
            dropdownList.postValue(dropdownArray)
        }
    }

    fun getTicketAvailability(idTicketType: String, idEvento: String, idFuncion: String, repository: Repository) {
        viewModelScope.launch {
            val ticketsData = getCurrentTicketsFunRequirement(idEvento, idFuncion, repository)
            for (ticket in ticketsData) {
                if (ticket.first == idTicketType) {
                    val ticketInfo = Pair(ticket.second, ticket.third)
                    ticketAvailability.postValue(ticketInfo)
                    break
                }
            }
        }
    }

}