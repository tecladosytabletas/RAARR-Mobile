package com.example.appatemporal.data.requirements

import android.util.Log
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class AddNewProjectRequirement {
    /*
    * Function to create an project item in the local database
    * @param project - The entity called Proyecto
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun createProject(project: Proyecto, repository:Repository){

        repository.insertProyecto(project)

    }
}