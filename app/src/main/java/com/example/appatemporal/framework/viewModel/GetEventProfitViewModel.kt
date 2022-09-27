package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetProfitsEvent
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class GetEventProfitViewModel : ViewModel() {
    val eventProfit = MutableLiveData<Int>()
    private var generalProfitsEvent = GetProfitsEvent()

    fun getEventProfit(eid: String, repository: Repository) {
        viewModelScope.launch {
            val event_profit = generalProfitsEvent(eid, repository)
            eventProfit.postValue(event_profit)
        }
    }
}