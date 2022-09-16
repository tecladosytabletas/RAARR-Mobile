package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.data.requirements.ProyectoOrganizadorRequirement
import com.example.appatemporal.domain.Repository

class ProyectoOrganizadorViewModel: ViewModel() {
    private val requirement = ProyectoOrganizadorRequirement()

    suspend fun removeProject(proyecto: Proyecto, repository: Repository){
        requirement.deleteProject(proyecto, repository)
    }
    suspend fun updateProject(proyecto: Proyecto, repository: Repository){
        requirement.updateProject(proyecto, repository)
    }

    suspend fun getProjects(repository: Repository): List<Proyecto>{
        return requirement.getProjects(repository)
    }

    fun countPendingActivities(repository: Repository,id_a: Int, id_e: Int): Int{
        return requirement.countPendingActivities(repository, id_a, id_e)
    }
}