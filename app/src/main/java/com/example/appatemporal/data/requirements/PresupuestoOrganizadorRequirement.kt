package com.example.appatemporal.data.requirements
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class PresupuestoOrganizadorRequirement {

    /*
    * Function to update presupuesto of a project
    * @param presupuestoNew - The String that is the new presupuesto of the project
    * @param id - The Int that is the id of the project that will be updated
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun updatePresupuestos(presupuestoNew: Double, id: Int, repository: Repository){
        repository.updatePresupuesto(presupuestoNew,id)
    }

    /*
    * Function to get the project that a project has according to the local database according
    * to it´s id
    * @param id - The id of the project the user selected
    * @param repository - The context to access the resources assigned to the local database
    * @return -  the project that has the same id project
    */
    suspend fun getProyectoById(id: Int, repository: Repository): Proyecto {
        return repository.getProyectoById(id)
    }

    /*
    * Function to update meta of a project
    * @param metaNew - The Double that is the new meta of the project
    * @param id - The Int that is the id of the project that will be updated
    * @param repository - The context to access the resources assigned to the local database
    */
    suspend fun updateMeta(metaNew: Double, id: Int, repository: Repository){
        repository.updateMeta(metaNew,id)
    }
}