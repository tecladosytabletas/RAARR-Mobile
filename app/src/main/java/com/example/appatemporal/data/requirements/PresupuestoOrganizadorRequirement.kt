package com.example.appatemporal.data.requirements

import com.example.appatemporal.domain.Repository

class PresupuestoOrganizadorRequirement {

    suspend fun updatePresupuestos(presupuestoNew: Double, id: Int, repository: Repository){
        repository.updatePresupuesto(presupuestoNew,id)
    }

}