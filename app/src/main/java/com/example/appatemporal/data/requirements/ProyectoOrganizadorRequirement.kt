package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class ProyectoOrganizadorRequirement {

    suspend fun deleteProject(project: Proyecto, repository: Repository) {
        var projectToDelete = repository.getProyectoById(project.id_proyecto)
        repository.deleteProyecto(projectToDelete)
    }

    suspend fun updateProject(project: Proyecto, repository: Repository){
        var projectToUpdate = repository.getProyectoById(project.id_proyecto)
        projectToUpdate.nombre_proyecto = project.nombre_proyecto
        projectToUpdate.fecha_inicio = project.fecha_inicio

        repository.updateProyecto(projectToUpdate)
    }

    suspend fun getProjects(repository: Repository): List<Proyecto>{
        return repository.getAllProyectos()
    }

    suspend fun getProjectById(id: Int, repository: Repository): Proyecto{
        return repository.getProyectoById(id)
    }
}