package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.requirements.AddActivityRequirement
import com.example.appatemporal.domain.Repository

class AddNewActivityViewModel: ViewModel() {
    private val requirement = AddActivityRequirement()
    suspend fun addNewActividad(actividad: Actividad, repository: Repository){

        requirement.createActividad(actividad, repository)


    }
}