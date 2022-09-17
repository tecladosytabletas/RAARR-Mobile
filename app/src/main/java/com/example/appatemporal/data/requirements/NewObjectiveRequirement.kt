package com.example.appatemporal.data.requirements

import com.example.appatemporal.data.localdatabase.entities.Objetivo
import com.example.appatemporal.domain.Repository

class NewObjectiveRequirement {
    suspend fun createObjective(objective: Objetivo, repository: Repository){
        repository.insertObjetivo(objective)
    }
}