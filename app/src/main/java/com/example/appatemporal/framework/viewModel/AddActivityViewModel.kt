package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.requirements.AddActivityRequirement
import com.example.appatemporal.domain.Repository

class AddActivityViewModel: ViewModel() {
    private val requirement = AddActivityRequirement()
    suspend fun getActivities(id:Int, repository: Repository): List<Actividad> {
        return requirement.getActivities(id, repository)
    }


}