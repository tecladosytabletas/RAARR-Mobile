package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.requirements.TasksActivityRequirement
import com.example.appatemporal.domain.Repository

class DeleteActivityViewModel : ViewModel(){
    private val requirement = TasksActivityRequirement()

    suspend fun removeActividad(actividad: Actividad, repository: Repository){
        requirement.deleteActividad(actividad, repository)
    }
    suspend fun updateActividad(actividad: Actividad, repository: Repository){
        requirement.updateActividad(actividad, repository)
    }

    suspend fun getActivities(repository: Repository): List<Actividad>{
        return requirement.getActivities(repository)
    }
}