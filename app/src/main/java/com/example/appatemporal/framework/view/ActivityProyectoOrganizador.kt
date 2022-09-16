package com.example.appatemporal.framework.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityProyectoOrganizadorBinding
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import kotlinx.coroutines.launch

class ActivityProyectoOrganizador: AppCompatActivity() {
    private val viewModel: ProyectoOrganizadorViewModel by viewModels()
    private lateinit var binding: ActivityProyectoOrganizadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProyectoOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var myExtras :Bundle? = intent.extras

        val stringToDo = "Actividades por completar:".plus(" ").plus(myExtras?.getString("doneActivities"))
        val stringCompleted = "Actividades completadas:".plus(" ").plus(myExtras?.getString("pendingActivities"))
        binding.activitiesCompleted.text = stringToDo
        binding.activitiesToDo.text = stringCompleted
        binding.projectName.text = myExtras?.getString("nombre_proyecto")

    }
}
