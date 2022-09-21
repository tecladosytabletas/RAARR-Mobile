package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetEventCountRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class CountEventViewModel : ViewModel() {

    val count = MutableLiveData<Int>()
    private var getEventCountRequirement = GetEventCountRequirement()

    fun countEvent(uid: String, repository: Repository) {
        viewModelScope.launch {
            val countEvents = getEventCountRequirement(uid, repository)
            count.postValue(countEvents)
        }
    }
}