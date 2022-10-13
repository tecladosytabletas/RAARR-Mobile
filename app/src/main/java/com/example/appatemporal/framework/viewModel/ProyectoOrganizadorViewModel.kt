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
    // variable to access the add project requirement docs
    private val requirement = ProyectoOrganizadorRequirement()
    // variable that is a live data list of projects
    val projects = MutableLiveData<List<Proyecto>>()
    // variable that is a live data project
    val project = MutableLiveData<Proyecto>()

    /**
    * Function to delete a project and establish the list projects again
    * @param proyecto - a variable that represents that will extend the entity called Proyecto
    * @param repository - The context to access the resources assigned to the local database
    */
    fun removeProject(proyecto: Proyecto, repository: Repository){
        viewModelScope.launch {
            requirement.deleteProject(proyecto, repository)
            val project = requirement.getProjects(repository)
            projects.setValue(project)
        }
    }

    /**
    * Function to update a project
    * @param name - The String that is the name of the project
    * @param date - The String that is the date of the project
    * @param time - The String that is the time of the project
    * @param id - The Int that is the id of the project
    * @param repository - The context to access the resources assigned to the local database
    */
    fun updateModify(name: String, date: String,time:String,id:Int ,repository: Repository){
        viewModelScope.launch {
            requirement.updateMod(name,date,time,id,repository)
        }

    }

    /**
    * Function to get all projects according to the local database and establish the list projects again
    * @param repository - The context to access the resources assigned to the local database
    * @return - A list containing all projects
    */
    fun getProjects(repository: Repository){
        viewModelScope.launch {
            val project = requirement.getProjects(repository)
            projects.setValue(project)
        }
    }

    /** Function that we use to count all the activities with a certain status attribute registered on a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project the user selected
    * @param stringStatus - The string that it will use to search in activities
    * @return The number of activities the function found
    */
    fun countPendingActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return requirement.countPendingActivities(repository, id_a, stringStatus)
    }

    /** Function that we use to count all the activities with a certain status attribute registered on a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project the user selected
    * @param stringStatus - The string that it will use to search in activities
    * @return The number of activities the function found
    */
    fun countDoneActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return requirement.countDoneActivities(repository, id_a, stringStatus)
    }
    /** Function that we use to count all the activities registered on a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project the user selected
    * @return The number of all the activities found in the local database
    */
    fun countAllActivities(repository: Repository, id_a: Int): Int{
        return requirement.countAllActivities(repository, id_a)
    }

    /**
    * Function used to extract all the projects that has a specific status
    * @param stringStatus - The boolean that will be used to search in the local database
    * @param repository - The context to access the resources assigned to the local database
    */
    fun getAllProjectsCompleted(stringStatus: Boolean, repository: Repository) {
        viewModelScope.launch {
            val list = requirement.filterProjectsByStatus(stringStatus, repository)
            projects.setValue(list)
        }
    }
}
