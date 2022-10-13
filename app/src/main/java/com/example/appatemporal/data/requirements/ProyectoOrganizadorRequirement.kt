package com.example.appatemporal.data.requirements

import android.util.Log
import com.example.appatemporal.data.localdatabase.entities.Estatus
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class ProyectoOrganizadorRequirement {

    /*
    * Function to delete a project
    * @param project - a variable that represents that will extend the entity called Proyecto
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun deleteProject(project: Proyecto, repository: Repository) {
        var projectToDelete = repository.getProyectoById(project.id_proyecto)
        repository.deleteProyecto(projectToDelete)
    }
    /*
    * Function to update a project
    * @param name - The String that is the name of the project
    * @param date - The String that is the date of the project
    * @param time - The String that is the time of the project
    * @param id - The Int that is the id of the project
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun updateMod(name: String, date: String, time: String, id: Int, repository: Repository){
        repository.updateModifyProyect(name,date,time,id)
    }

    /*
    * Function to get all projects according to the local database
    * @param repository - The context to access the resources assigned to the local database
    * @return - A list containing all projects
    */

    suspend fun getProjects(repository: Repository): List<Proyecto>{
        return repository.getAllProyectos()
    }

    /*
    * Function to get the number of all the pending activities
    * @param stringStatus - The String that is the value of the status of the activity
    * @param id_a - The Int that is the id of the activities
    * @param repository - The context to access the resources assigned to the local database
    * @return - the function from de Dao that calls the query that counts and returns the number
    * of pending activities
    */

    fun countPendingActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return repository.countPendingActivities(id_a, stringStatus)
    }

    /*
    * Function to get the number of all the activities done
    * @param stringStatus - The String that is the value of the status of the activity
    * @param id_a - The Int that is the id of the activities
    * @param repository - The context to access the resources assigned to the local database
    * @return - the function from de Dao that calls the query that counts and returns the number
    * of activities that are done
    */

    fun countDoneActivities(repository: Repository, id_a: Int, stringStatus: String): Int{
        return repository.countDoneActivities(id_a, stringStatus)
    }

    /*
    * Function to get the number of all the activities
    * @param id_a - The Int that is the id of the activities
    * @param repository - The context to access the resources assigned to the local database
    * @return - the function from de Dao that calls the query that counts and returns the number
    * of activities
    */

    fun countAllActivities(repository: Repository, id_a: Int): Int{
        return repository.countAllActivities(id_a)
    }

    /*
    * Function to get all the projects depending on their status
    * @param stringStatus - The boolean that is the value of the status that will be searched for
    * @param repository - The context to access the resources assigned to the local database
    * @return - the function from de Dao that calls the query that returns the projects depending
    * on their status
    */

    suspend fun filterProjectsByStatus(stringStatus:Boolean ,repository: Repository): List<Proyecto> {
        return repository.filterProjectsByStatus(stringStatus)
    }
}