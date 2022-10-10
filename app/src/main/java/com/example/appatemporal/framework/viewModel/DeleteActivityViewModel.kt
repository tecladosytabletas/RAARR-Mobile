package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.localdatabase.entities.Estatus
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.data.requirements.TasksActivityRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class DeleteActivityViewModel : ViewModel(){
    private val requirement = TasksActivityRequirement()
    val activities = MutableLiveData<List<Actividad>>()
    val project = MutableLiveData<Proyecto>()

    /*
    * Function to delete an activity
    * @param actividad - a variable that represents that will extend the entity called Actividad
    * @param repository - The context to access the resources assigned to the local database
    */
    fun removeActividad(id:Int, actividad: Actividad, repository: Repository){
        viewModelScope.launch {
            requirement.deleteActividad(actividad, repository)
            val list = requirement.getAllActivitiesId(id,repository)
            activities.setValue(list)
        }
    }

    /*
    * Function to update an activity
    * @param actividad - The entity called Actividad
    * @param repository - The context to access the resources assigned to the local database
    */
    fun updateActividad(nombre:String, estatus:String, area:String, prioridad:String, id: Int, repository: Repository){
        viewModelScope.launch {
            requirement.updateActividad(nombre, estatus, area, prioridad, id, repository)
        }
    }
    /*
    * Function to get all activities that a project has according to the local database
    * @param id - The id of the project the user selected
    * @param repository - The context to access the resources assigned to the local database
    */
    fun getAllActivitiesid(id:Int, repository: Repository){
        viewModelScope.launch {
            val list = requirement.getAllActivitiesId(id,repository)
            activities.setValue(list)
        }
    }
    /*
    * Function to get all activities that has the same prioridad as the one the function receives
    * @param id - The id of the project the user selected
    * @param stringStatus - A string that it will use to search in the activities
    * @param repository - The context to access the resources assigned to the local database
    */
    fun getAllActivitiesPrioridad(id:Int, prioridad: String, repository: Repository) {
        viewModelScope.launch {
            val list = requirement.filterActivitiesByPriority(id,prioridad, repository)
            activities.setValue(list)
        }
    }
    /*
    * Function to get all activities that has the same area as the one the function receives
    * @param id - The id of the project the user selected
    * @param stringStatus - A string that it will use to search in the activities
    * @param repository - The context to access the resources assigned to the local database
    */
    fun getAllActivitiesArea(id:Int, area: String, repository: Repository) {
        viewModelScope.launch {
            val list = requirement.filterActivitiesByArea(id,area, repository)
            activities.setValue(list)
        }
    }
    /*
    * Function to get all activities that has the same status as the one the function receives
    * @param id - The id of the project the user selected
    * @param stringStatus - A string that it will use to search in the activities
    * @param repository - The context to access the resources assigned to the local database
    */
    fun getAllActivitiesEstatus(id:Int, area: String, repository: Repository) {
        viewModelScope.launch {
            val list = requirement.filterActivitiesByStatus(id,area, repository)
            activities.setValue(list)
        }
    }
    /* Function that we use to count all the activities with a certain status attribute registered on a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project the user selected
    * @param stringStatus - The string that it will use to search in activities
    */
    fun countDoneActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return requirement.countDoneActivities(repository, id_a, stringStatus)
    }
    /* Function that we use to count all the activities registered on a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project the user selected
    */
    fun countAllActivities(repository: Repository, id_a: Int): Int{
        return requirement.countAllActivities(repository, id_a)
    }
    /* Function that we use to update a status of a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project we need to consult
    * @param estatusNew - The string that will be used to update in the local database
    */
    fun updateEstatusCompletado(estatusNew: Boolean, id: Int, repository: Repository){
        viewModelScope.launch {
            requirement.updateEstatusCompletado(estatusNew, id, repository)
            val proyecto = requirement.getProyectoById(id, repository)
            project.postValue(proyecto)
        }
    }

}
