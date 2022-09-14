package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.requirements.TaskActivityRequirement
import com.example.appatemporal.domain.Repository

class TaskActivityViewModel: ViewModel() {
    val requirement = TaskActivityRequirement()

    suspend fun addActivity(activity: Actividad, repository: Repository){
        requirement.addActivity(activity, repository)
    }
}