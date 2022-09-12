package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.data.requirements.DeleteProjectRequirement
import com.example.appatemporal.domain.Repository

class DeleteProjectViewModel: ViewModel() {
    private val requirement = DeleteProjectRequirement()

    suspend fun removeProject(proyecto: Proyecto, repository: Repository){
        requirement.deleteProject(proyecto, repository)
    }
}