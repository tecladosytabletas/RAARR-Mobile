package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.domain.Repository

class TaskActivityRequirement {

    suspend fun addActivity(activity: Actividad, repository: Repository) {
        repository.insertActividad(activity)
    }
}