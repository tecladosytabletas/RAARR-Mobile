package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.ReportFailureRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

/**
 * Class that inherits from ViewModel
 */
class ReportFailureViewModel : ViewModel() {
    private val reportFailureRequirement = ReportFailureRequirement()

    /**
     * Adds report to Firestore database
     *
     * @param title: String -> the report's tittle
     * @param description -> the failure's description
     * @param repository: Repository -> Repository of the application
     */
    fun addFailure(title: String, description: String, repository: Repository) {
        viewModelScope.launch {
            reportFailureRequirement(title, description, repository)
        }
    }

}