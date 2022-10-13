package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.requirements.AddNewProjectRequirement
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository


class AddNewProjectViewModel: ViewModel() {
    // variable to access the add project requirement docs
    private val requirement = AddNewProjectRequirement()

    /**
    * Function to create an project item in the local database
    * @param proyecto - The entity called Proyecto
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun addNewProject(proyecto: Proyecto, repository: Repository){

        requirement.createProject(proyecto, repository)


    }

}
