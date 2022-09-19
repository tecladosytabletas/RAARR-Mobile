package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Costo
import com.example.appatemporal.data.requirements.TasksCostoRequirement
import com.example.appatemporal.domain.Repository

class DeleteCostoViewModel : ViewModel(){
    private val requirement = TasksCostoRequirement()

    suspend fun removeCosto(costo: Costo, repository: Repository){
        requirement.deleteCosto(costo, repository)
    }
//    suspend fun updateActividad(actividad: Actividad, repository: Repository){
//        requirement.updateProject(actividad, repository)
//    }

    suspend fun getCosto(repository: Repository): List<Costo>{
        return requirement.getCostos(repository)
    }
}