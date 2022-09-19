package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.data.requirements.PresupuestoOrganizadorRequirement
import com.example.appatemporal.domain.Repository

class PresupuestoOrganizadorViewModel: ViewModel()  {

        private val requirement = PresupuestoOrganizadorRequirement()

        suspend fun updatePrespuesto(presupuestoNew: Double, id: Int, repository: Repository){
            requirement.updatePresupuestos(presupuestoNew, id,repository)
        }
        suspend fun getProyectoByid(id: Int, repository: Repository):Proyecto{
            return requirement.getProyectoById(id,repository)
        }
        suspend fun updateMeta(metaNew: Double, id: Int, repository: Repository){
            requirement.updateMeta(metaNew, id,repository)
        }

}