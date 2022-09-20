package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.localdatabase.entities.Estatus
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.data.requirements.ProyectoOrganizadorRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class ProyectoOrganizadorViewModel: ViewModel() {
    private val requirement = ProyectoOrganizadorRequirement()

    val projects = MutableLiveData<List<Proyecto>>()

    fun removeProject(proyecto: Proyecto, repository: Repository){
        viewModelScope.launch {
            requirement.deleteProject(proyecto, repository)
            val project = requirement.getProjects(repository)
            projects.setValue(project)
        }
    }

    fun updateModify(name: String, date: String,time:String,id:Int ,repository: Repository){
        viewModelScope.launch {
            requirement.updateMod(name,date,time,id,repository)
        }

    }

    fun getProjects(repository: Repository){
        viewModelScope.launch {
            val project = requirement.getProjects(repository)
            projects.setValue(project)
        }

    }

    fun countPendingActivities(repository: Repository,id_a: Int, id_e: Int): Int{
        return requirement.countPendingActivities(repository, id_a, id_e)
    }

    suspend fun inserEstatus(repository: Repository, estatus: Estatus) {
        requirement.insertEstatus(estatus, repository)
    }
}