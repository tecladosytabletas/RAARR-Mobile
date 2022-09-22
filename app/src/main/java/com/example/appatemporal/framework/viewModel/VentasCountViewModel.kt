package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.network.GetVentasCountRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class VentasCountViewModel : ViewModel() {

    val ventas = MutableLiveData<Pair<Int, Int>>()
    private var getVentasCountRequirement = GetVentasCountRequirement()

    fun ventasEvent(uid: String, repository: Repository) {
        viewModelScope.launch {
            val countEvents = getVentasCountRequirement(uid, repository)
            ventas.postValue(countEvents)
        }
    }
}