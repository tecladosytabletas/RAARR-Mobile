package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Objetivo
import com.example.appatemporal.data.requirements.NewObjectiveRequirement
import com.example.appatemporal.domain.Repository

class NewObjectiveViewModel: ViewModel()  {

    private val requirement = NewObjectiveRequirement()

    suspend fun createObjectives(objetivo: Objetivo, repository: Repository){
        requirement.createObjective(objetivo,repository)
    }

}