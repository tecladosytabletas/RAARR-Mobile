package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetPMbyTicketsRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class GetPMbyTicketsViewModel : ViewModel()  {
    val countPM = MutableLiveData<Pair<Int, Int>>()
    private var getPMbyTicketsRequirement = GetPMbyTicketsRequirement()

    fun getPMbyTickets(eid: String, repository: Repository) {
        viewModelScope.launch {
            val event_name = getPMbyTicketsRequirement(eid, repository)
            countPM.postValue(event_name)
        }
    }
}