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

}