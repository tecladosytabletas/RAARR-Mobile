package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.network.APIService
import com.example.appatemporal.data.network.dataclasses.DC_Event

class VMEventProfits : ViewModel() {
    val API = APIService()
    fun fetchEventData(): LiveData<MutableList<DC_Event>> {
        val mutableData = MutableLiveData<MutableList<DC_Event>>()
        API.getEventData().observeForever{ it
            mutableData.value = it
        }
        return mutableData
    }
}
