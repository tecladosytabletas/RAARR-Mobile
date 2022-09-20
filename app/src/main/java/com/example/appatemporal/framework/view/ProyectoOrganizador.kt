package com.example.appatemporal.framework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.ProyectosOrganizadorBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ProjectsAdapter
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import kotlinx.coroutines.launch

class ProyectoOrganizador : AppCompatActivity() {
    private val  viewModel : ProyectoOrganizadorViewModel by viewModels()
    private lateinit var binding: ProyectosOrganizadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProyectosOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)

        viewModel.getProjects(repository)
        viewModel.projects.observe(this, Observer { projectList ->
            Log.d("Prueba", projectList.toString())
            binding.recyclerViewProjects.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewProjects.adapter = ProjectsAdapter(projectList, viewModel)
        })



        binding.tvNewProject.setOnClickListener {
            val intent = Intent(this, AddNewProjectForm::class.java)
            startActivity(intent)
        }

        binding.navbar.homeIcon.setOnClickListener {
            finish()
        }

        binding.navbar.budgetIcon.setOnClickListener {
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

        binding.navbar.ticketsIcon.setOnClickListener {
            finish()
        }

        binding.navbar.metricsIcon.setOnClickListener {
            finish()
        }
    }


}