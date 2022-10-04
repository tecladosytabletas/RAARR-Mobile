package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.domain.Repository

class TasksActivityRequirement {

    suspend fun deleteActividad(actividad: Actividad, repository: Repository) {
        var actividadToDelete = repository.getActividadById(actividad.id_actividad)
        repository.deleteActividad(actividadToDelete)
    }

    suspend fun updateActividad(nombre:String, estatus:String, area:String, prioridad:String, id: Int, repository: Repository){
        repository.updateActividad(nombre, estatus, area, prioridad, id)
    }

    suspend fun getActivities(repository: Repository): List<Actividad> {

       return repository.getAllActividades()
    }

    suspend fun getAllActivitiesId(id: Int,repository: Repository): List<Actividad> {
        return repository.getAllActividadById(id)
    }

    suspend fun filterActivitiesByStatus(idProyecto:Int, stringStatus:String ,repository: Repository): List<Actividad> {
        return repository.filterActivitiesByStatus(idProyecto, stringStatus)
    }

    suspend fun filterActivitiesByArea(idProyecto:Int, stringStatus:String ,repository: Repository): List<Actividad> {
        return repository.filterActivitiesByArea(idProyecto, stringStatus)
    }

    suspend fun filterActivitiesByPriority(idProyecto:Int, stringStatus:String ,repository: Repository): List<Actividad> {
        return repository.filterActivitiesByPriority(idProyecto, stringStatus)
    }
}