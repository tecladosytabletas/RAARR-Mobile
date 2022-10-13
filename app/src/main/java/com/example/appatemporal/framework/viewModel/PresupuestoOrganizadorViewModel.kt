package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.data.requirements.PresupuestoOrganizadorRequirement
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class PresupuestoOrganizadorViewModel: ViewModel()  {
        // variable to access the add project requirement docs
        private val requirement = PresupuestoOrganizadorRequirement()
        // variable that is a live data project
        val project = MutableLiveData<Proyecto>()

        /**
        * Function to update presupuesto of a project and establish the project again
        * @param presupuestoNew - The String that is the new presupuesto of the project
        * @param id - The Int that is the id of the project that will be updated
        * @param repository - The context to access the resources assigned to the local database
        */
        fun updatePrespuesto(presupuestoNew: Double, id: Int, repository: Repository){
            viewModelScope.launch {
                requirement.updatePresupuestos(presupuestoNew, id, repository)
                val proyecto = requirement.getProyectoById(id, repository)
                project.postValue(proyecto)
            }
        }

        /**
        * Function to get the project that a project has according to the local database according
        * to it´s id and establish the project again
        * @param id - The id of the project the user selected
        * @param repository - The context to access the resources assigned to the local database
        */
        fun getProyectoByid(id: Int, repository: Repository){
            viewModelScope.launch {
                val proyecto = requirement.getProyectoById(id, repository)
                project.postValue(proyecto)

            }
        }

        /**
        * Function to update meta of a project and establish the project again
        * @param metaNew - The Double that is the new meta of the project
        * @param id - The Int that is the id of the project that will be updated
        * @param repository - The context to access the resources assigned to the local database
        */
        fun updateMeta(metaNew: Double, id: Int, repository: Repository){
            viewModelScope.launch {
                requirement.updateMeta(metaNew, id, repository)
                val proyecto = requirement.getProyectoById(id, repository)
                project.postValue(proyecto)
            }
        }

}
