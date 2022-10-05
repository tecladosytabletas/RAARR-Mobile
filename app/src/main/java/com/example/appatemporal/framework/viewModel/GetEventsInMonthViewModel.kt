package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetEventsInMonth
import com.example.appatemporal.data.requirements.GetAllEvents
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.EventModel
import com.example.appatemporal.domain.models.EventsInMonth
import kotlinx.coroutines.launch

class GetEventsInMonthViewModel : ViewModel()  {

    val eventsMonth = MutableLiveData<MutableList<EventsInMonth>>()
    private var getEventsInMonth = GetEventsInMonth()

    private var requirement = GetAllEvents()
    val allEvents = MutableLiveData<List<EventModel>>()

    fun getEventsMonth(eD: Int, eM: Int, repository: Repository) {
        viewModelScope.launch {
            val eventsInMonth = getEventsInMonth(eD,eM,repository)
            eventsMonth.postValue(eventsInMonth)
        }
    }

    fun getAllEvents(repository: Repository){
        viewModelScope.launch {
            val eventsInMonth = requirement.getAllEvents(repository)
            allEvents.setValue(eventsInMonth)
        }
    }
}