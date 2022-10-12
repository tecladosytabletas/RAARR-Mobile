package com.example.appatemporal.data.requirements

import android.util.Log
import com.example.appatemporal.data.localdatabase.entities.Estatus
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class ProyectoOrganizadorRequirement {

    suspend fun deleteProject(project: Proyecto, repository: Repository) {
        var projectToDelete = repository.getProyectoById(project.id_proyecto)
        repository.deleteProyecto(projectToDelete)
    }

    suspend fun updateMod(name: String, date: String, time: String, id: Int, repository: Repository){
        repository.updateModifyProyect(name,date,time,id)
    }

    suspend fun getProjects(repository: Repository): List<Proyecto>{
        return repository.getAllProyectos()
    }

    fun countPendingActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return repository.countPendingActivities(id_a, stringStatus)
    }

    fun countDoneActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return repository.countDoneActivities(id_a, stringStatus)
    }

    fun countAllActivities(repository: Repository, id_a: Int): Int{
        return repository.countAllActivities(id_a)
    }
    suspend fun filterProjectsByStatus(stringStatus:Boolean ,repository: Repository): List<Proyecto> {
        return repository.filterProjectsByStatus(stringStatus)
    }
}