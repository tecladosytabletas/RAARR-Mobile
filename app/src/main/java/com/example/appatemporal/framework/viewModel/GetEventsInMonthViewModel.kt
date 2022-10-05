package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetEventsInMonth
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.EventsInMonth
import kotlinx.coroutines.launch

class GetEventsInMonthViewModel : ViewModel()  {

    val eventsMonth = MutableLiveData<MutableList<EventsInMonth>>()
    private var getEventsInMonth = GetEventsInMonth()

    fun getEventsMonth(eD: Int, eM: Int, repository: Repository) {
        viewModelScope.launch {
            val eventsInMonth = getEventsInMonth(eD,eM,repository)
            eventsMonth.postValue(eventsInMonth)
        }
    }
}