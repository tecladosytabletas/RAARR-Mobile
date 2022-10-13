package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class TasksActivityRequirement {

    /*
    * Function to delete an activity
    * @param actividad - a variable that represents that will extend the entity called Actividad
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun deleteActividad(actividad: Actividad, repository: Repository) {
        var actividadToDelete = repository.getActividadById(actividad.id_actividad)
        repository.deleteActividad(actividadToDelete)
    }

    /*
    * Function to update an activity
    * @param actividad - The entity called Actividad
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun updateActividad(nombre:String, estatus:String, area:String, prioridad:String, id: Int, repository: Repository){
        repository.updateActividad(nombre, estatus, area, prioridad, id)
    }

    /*
    * Function to get all activities that a project has according to the local database
    * @param id - The id of the project the user selected
    * @param repository - The context to access the resources assigned to the local database
    * @return - A list containing all activities that has the same id project registered
    */
    suspend fun getAllActivitiesId(id: Int,repository: Repository): List<Actividad> {
        return repository.getAllActividadById(id)
    }
    /*
    * Function to get all activities that has the same status as the one the function receives
    * @param id - The id of the project the user selected
    * @param stringStatus - A string that it will use to search in the activities
    * @param repository - The context to access the resources assigned to the local database
    * @return - A list containing all activities that has the same id project registered
    */
    suspend fun filterActivitiesByStatus(idProyecto:Int, stringStatus:String ,repository: Repository): List<Actividad> {
        return repository.filterActivitiesByStatus(idProyecto, stringStatus)
    }
    /*
    * Function to get all activities that has the same area as the one the function receives
    * @param id - The id of the project the user selected
    * @param stringStatus - A string that it will use to search in the activities
    * @param repository - The context to access the resources assigned to the local database
    * @return - A list containing all activities that has the same id project registered
    */
    suspend fun filterActivitiesByArea(idProyecto:Int, stringStatus:String ,repository: Repository): List<Actividad> {
        return repository.filterActivitiesByArea(idProyecto, stringStatus)
    }
    /*
    * Function to get all activities that has the same prioridad as the one the function receives
    * @param id - The id of the project the user selected
    * @param stringStatus - A string that it will use to search in the activities
    * @param repository - The context to access the resources assigned to the local database
    * @return - A list containing all activities that has the same id project registered
    */
    suspend fun filterActivitiesByPriority(idProyecto:Int, stringStatus:String ,repository: Repository): List<Actividad> {
        return repository.filterActivitiesByPriority(idProyecto, stringStatus)
    }



    // Things for filter completed projects

    /* Function that we use to count all the activities with a certain status attribute registered on a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project the user selected
    * @param stringStatus - The string that it will use to search in activities
    * @return The number of activities the function found
    */
    fun countDoneActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return repository.countDoneActivities(id_a, stringStatus)
    }

    /* Function that we use to count all the activities registered on a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project the user selected
    * @return The number of all the activities found in the local database
    */
    fun countAllActivities(repository: Repository, id_a: Int): Int{
        return repository.countAllActivities(id_a)
    }

    /* Function that we use to extract a project according its id
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project we need to consult
    * @return The project that was found
    */
    suspend fun getProyectoById(id: Int, repository: Repository): Proyecto {
        return repository.getProyectoById(id)
    }
    /* Function that we use to update a status of a project
    * @param repository - The context to access the resources assigned to the local database
    * @param id - The id of the project we need to consult
    * @param estatusNew - The string that will be used to update in the local database
    */
    suspend fun updateEstatusCompletado(estatusNew: Boolean, id: Int, repository: Repository){
        repository.updateEstatusCompletado(estatusNew,id)
    }
}