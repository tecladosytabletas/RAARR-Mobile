package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.requirements.EventosByCategoriasRequirement
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.CategoryModel
import com.example.appatemporal.domain.models.EventModel
import kotlinx.coroutines.launch

class EventosByCategoriasViewModel: ViewModel() {
    private var requirement = EventosByCategoriasRequirement()

    val events = MutableLiveData<List<EventModel>>()
    val eventsIds = MutableLiveData<List<String>>()
    val categoryId = MutableLiveData<String>()
    val eventsByCategory = MutableLiveData<List<EventModel>>()
    fun getEventsByCategory(repository: Repository, categoryName:String) {
        viewModelScope.launch {
            events.setValue(requirement.getEvents(repository))
            categoryId.setValue(requirement.getCategoryIdByName(repository, categoryName))
            eventsIds.setValue(requirement.getIdsOfEventosWithidCategoria(categoryId.value!!, repository))
            val finalEvents: List<EventModel> = listOf()
            for(event in events.value!!){
                if(event.id in eventsIds.value!!){
                    finalEvents.plus(event)
                }
            }
            eventsByCategory.setValue(finalEvents)


        }
    }

}