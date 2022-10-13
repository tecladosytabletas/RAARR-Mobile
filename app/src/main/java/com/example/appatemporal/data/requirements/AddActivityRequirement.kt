package com.example.appatemporal.data.requirements

import android.util.Log
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.domain.Repository

class AddActivityRequirement {

    /*
    * Function to create an activity item in the local database
    * @param actividad - The entity called Actividad
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun createActividad(actividad: Actividad, repository:Repository){
        repository.insertActividad(actividad)
    }
}