package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.*
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

/**
 * Class that inherits from ViewModel
 */

class DetailedMetricsViewModel : ViewModel() {

    /**
     * Gets the number of sales by payment method
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     */
    val countPM = MutableLiveData<MutableMap<String, Int?>>()
    private var getPMbyTicketsRequirement = GetPMbyTicketsRequirement()
    fun getPMbyTickets(eid: String, repository: Repository) {
        viewModelScope.launch {
            val PMbyTickets = getPMbyTicketsRequirement(eid, repository)
            countPM.postValue(PMbyTickets)
        }
    }

    /**
     * Gets the name of an event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     */
    val eventName = MutableLiveData<String>()
    private var getEventNameRequirement = GetEventNameRequirement()
    fun getEventName(eid: String, repository: Repository) {
        viewModelScope.launch {
            val event_name = getEventNameRequirement(eid, repository)
            eventName.postValue(event_name)
        }
    }

    /**
     * Gets the revenue/profits of an event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     */
    val eventProfit = MutableLiveData<Int>()
    private var generalProfitsEvent = GetProfitsEvent()
    fun getEventProfit(eid: String, repository: Repository) {
        viewModelScope.launch {
            val event_profit = generalProfitsEvent(eid, repository)
            eventProfit.postValue(event_profit)
        }
    }

    /**
     * Gets the name of every ticket type, number of sales per ticket type as well as the number
     * of assists per ticket type of an event
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     */
    val eventsTicketsTypeSA = MutableLiveData<MutableMap<String, Pair<Int?, Int?>>>()
    private var getTypeSATickets = GetTypeSATickets()
    fun getTypeSA(eid: String, repository: Repository) {
        viewModelScope.launch {
            val events_TicketsTypeSA = getTypeSATickets(eid, repository)
            eventsTicketsTypeSA.postValue(events_TicketsTypeSA)
        }
    }

    /**
     * Gets the revenue/profits of an event by payment method
     *
     * @param eid: String -> event uid
     * @param repository: Repository -> Repository of the application
     */
    val revenueByPM = MutableLiveData<MutableMap<String, Int?>>()
    private var getRevenuebyPM = GetRevenuebyPM()
    fun getRevenuePM(eid: String, repository: Repository) {
        viewModelScope.launch {
            val eventRevenueByPM = getRevenuebyPM(eid, repository)
            revenueByPM.postValue(eventRevenueByPM)
        }
    }

}