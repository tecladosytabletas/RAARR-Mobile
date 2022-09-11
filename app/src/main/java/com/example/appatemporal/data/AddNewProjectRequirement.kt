package com.example.appatemporal.data

import android.content.Intent
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.ProyectoOrganizador
import kotlinx.coroutines.launch

class AddNewProjectRequirement {

    suspend fun createProject(project: Proyecto, repository:Repository){

        repository.insertProyecto(project)
        repository.getAllProyectos().forEach {
            Log.d("Proyecto", it.toString())
        }

    }
}