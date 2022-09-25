package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetEventNameRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class GetEventNameViewModel :  ViewModel() {
    val eventName = MutableLiveData<String>()
    private var getEventNameRequirement = GetEventNameRequirement()

    fun getEventName(eid: String, repository: Repository) {
        viewModelScope.launch {
            val event_name = getEventNameRequirement(eid, repository)
            eventName.postValue(event_name)
        }
    }
}