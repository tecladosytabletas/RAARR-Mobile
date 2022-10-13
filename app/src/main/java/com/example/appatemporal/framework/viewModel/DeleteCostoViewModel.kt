package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Costo
import com.example.appatemporal.data.requirements.TasksCostoRequirement
import com.example.appatemporal.domain.Repository

class DeleteCostoViewModel : ViewModel(){
    private val requirement = TasksCostoRequirement()
    /*
    * Function to delete a costo
    * @param costo - a variable that represents that will extend the entity called Costo
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun removeCosto(costo: Costo, repository: Repository){
        requirement.deleteCosto(costo, repository)
    }
    /*
    * Function to update a costo
    * @param costo - The entity called Costo
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun updateCosto(costo: Costo, repository: Repository) {
        requirement.updateCosto(costo, repository)
    }
    /*
    * Function to get all costos that a project has according to the local database
    * @param idProject - The id of the project the user selected
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun getCosto(repository: Repository, idProject:Int): List<Costo>{
        return requirement.getCostos(repository, idProject)
    }
}