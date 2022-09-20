package com.example.appatemporal.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appatemporal.data.network.dataclasses.DC_Event
import com.google.firebase.firestore.FirebaseFirestore

class APIService {
    fun getEventData(): LiveData<MutableList<DC_Event>> {
        val mutableData = MutableLiveData<MutableList<DC_Event>>()
        FirebaseFirestore.getInstance().collection("Eventos")
            .get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<DC_Event>()
                for(document in result){
                    val EventName = document.getString("EventName")
                    val EventCategory = document.getString("EventCategory")
                    val event = DC_Event(EventName!!,EventCategory!!)
                    listData.add(event)
                }
                mutableData.value = listData
            }
        return mutableData
    }
}