package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetRatingRequirement
import com.example.appatemporal.data.GetRevenueRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class GetRevenueViewModel : ViewModel() {
    val revenue = MutableLiveData<Int>()
    private var getRevenueRequirement = GetRevenueRequirement()

    fun getRevenue(uid: String, repository: Repository) {
        viewModelScope.launch {
            val revenueCount = getRevenueRequirement(uid, repository)
            revenue.postValue(revenueCount)
        }
    }
}