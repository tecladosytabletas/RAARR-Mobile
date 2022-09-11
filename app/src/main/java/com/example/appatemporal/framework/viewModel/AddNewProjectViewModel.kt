package com.example.appatemporal.framework.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.AddNewProjectRequirement
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.ProyectoOrganizador
import kotlinx.coroutines.launch

class AddNewProjectViewModel: ViewModel() {
    val requirement = AddNewProjectRequirement()
    suspend fun addNewProject(proyecto: Proyecto, repository: Repository){
        requirement.createProject(proyecto, repository)


    }
}