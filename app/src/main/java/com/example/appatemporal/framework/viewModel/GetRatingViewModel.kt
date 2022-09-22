package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetRatingRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class GetRatingViewModel : ViewModel() {
    val rating = MutableLiveData<Float>()
    private var getRatingRequirement = GetRatingRequirement()

    fun getRating(uid: String, repository: Repository) {
        viewModelScope.launch {
            val ratingCount = getRatingRequirement(uid, repository)
            rating.postValue(ratingCount)
        }
    }
}