package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.domain.Repository

class AddActivityRequirement {

    suspend fun getActivities(id:Int , repository: Repository  ): List<Actividad> {
        return repository.getTasks(id)
    }
}