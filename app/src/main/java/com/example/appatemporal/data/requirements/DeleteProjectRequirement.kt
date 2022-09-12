package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class DeleteProjectRequirement {
    suspend fun deleteProject(project: Proyecto, repository: Repository) {
        var projectToDelete = repository.getProyectoById(project.id_proyecto)
        repository.deleteProyecto(projectToDelete)
    }
}