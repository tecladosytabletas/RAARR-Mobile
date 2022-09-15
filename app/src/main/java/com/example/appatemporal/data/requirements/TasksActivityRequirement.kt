package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.domain.Repository

class TasksActivityRequirement {

    suspend fun deleteProject(actividad: Actividad, repository: Repository) {
        var actividadToDelete = repository.getActividadById(actividad.id_actividad)
        repository.deleteActividad(actividadToDelete)
    }

    suspend fun updateProject(actividad: Actividad, repository: Repository){
        var actividadToUpdate = repository.getActividadById(actividad.id_actividad)
        actividadToUpdate.nombre_actividad = actividad.nombre_actividad

        repository.updateActividad(actividadToUpdate)
    }

    suspend fun getProjects(repository: Repository): List<Actividad>{
        return repository.getAllActividades()
    }
}