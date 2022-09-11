package com.example.appatemporal.framework.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.ProyectoOrganizador
import kotlinx.coroutines.launch

class AddNewProjectViewModel: ViewModel() {
    private fun createProject(context: Context, name: String, date:String, repository: Repository){
        val project: Proyecto = Proyecto(0, 1, name, date)

        lifecycleScope.launch{
            repository.insertProyecto(project)
            repository.getAllProyectos().forEach {
                Log.d("Proyecto", it.toString())
            }
            repository.deleteAllProyectos()


        }


        // Go back to main activity
        val intent = Intent(this, ProyectoOrganizador::class.java)
        startActivity(intent)

    }
}