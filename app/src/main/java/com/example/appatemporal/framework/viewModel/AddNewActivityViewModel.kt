package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.requirements.AddActivityRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class AddNewActivityViewModel: ViewModel() {
    // variable to access the add activity requirement docs
    private val requirement = AddActivityRequirement()

    /*
    * Function to create an activity item in the local database
    * @param actividad - The entity called Actividad
    * @param repository - The context to access the resources assigned to the local database
    */
    fun addNewActividad(actividad: Actividad, repository: Repository){
        viewModelScope.launch {
            requirement.createActividad(actividad, repository)
        }


    }
}