package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetEEventRating
import com.example.appatemporal.data.GetTicketsSAEvent
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

/**
 * Class that inherits from ViewModel
 */

class GraphicsEventDetailViewModel : ViewModel() {

    /**
     * Gets the sum of all ratings, the number of times a rating appears
     * and the general rating of a specific event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     */
    val ratingExt = MutableLiveData<MutableList<Float>>()
    private var getEEventRating = GetEEventRating()
    fun getExtEventRating(eid: String, repository: Repository) {
        viewModelScope.launch {
            val event_rating = getEEventRating(eid, repository)
            ratingExt.postValue(event_rating)
        }
    }

    /**
     * Gets the number of sold tickets and the number of tickets
     * marked as assisted of a specific event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     */
    val eventTicketsSAEvent = MutableLiveData<Pair<Int,Int>>()
    private var getTicketsSAEvent = GetTicketsSAEvent()
    fun getTicketsSA(eid: String, repository: Repository) {
        viewModelScope.launch {
            val eventTSA = getTicketsSAEvent(eid, repository)
            eventTicketsSAEvent.postValue(eventTSA)
        }
    }

}