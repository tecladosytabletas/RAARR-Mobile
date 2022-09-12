package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class UpdateProjectRequirement {

    suspend fun updateProject(project: Proyecto, repository: Repository){
        var projectToUpdate = repository.getProyectoById(project.id_proyecto)
        projectToUpdate.nombre_proyecto = project.nombre_proyecto
        projectToUpdate.fecha_inicio = project.fecha_inicio

        repository.updateProyecto(projectToUpdate)
    }
}