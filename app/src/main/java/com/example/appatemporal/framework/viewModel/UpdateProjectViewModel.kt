package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository

class UpdateProjectViewModel:ViewModel() {
    private val requirement = UpdateProjectRequirement()

    suspend fun editProject(proyecto: Proyecto, repository: Repository){
        requirement.updateProject(proyecto, repository)
    }
}