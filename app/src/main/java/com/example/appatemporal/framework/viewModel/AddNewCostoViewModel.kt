package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Costo
import com.example.appatemporal.data.requirements.AddCostoRequirement
import com.example.appatemporal.domain.Repository

class AddNewCostoViewModel: ViewModel() {
    private val requirement = AddCostoRequirement()
    /**
    * Function to create a costo item in the local database
    * @param costo - The entity called Costo
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun addNewCosto(costo: Costo, repository: Repository){

        requirement.createCosto(costo, repository)


    }
}