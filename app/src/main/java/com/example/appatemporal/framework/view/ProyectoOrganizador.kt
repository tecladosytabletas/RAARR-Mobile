package com.example.appatemporal.framework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ProyectosOrganizadorBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ProjectsAdapter
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProyectoOrganizador : AppCompatActivity() {
    private val  viewModel : ProyectoOrganizadorViewModel by viewModels()
    private lateinit var binding: ProyectosOrganizadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProyectosOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        lifecycleScope .launch {
            initRecyclerView(repository)
        }

        binding.tvNewProject.setOnClickListener {
            val intent = Intent(this, AddNewProjectForm::class.java)
            startActivity(intent)
        }
    }

    private suspend fun initRecyclerView(repository: Repository) {
        val projectList = viewModel.getProjects(repository)
        binding.recyclerViewProjects.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProjects.adapter = ProjectsAdapter(projectList)
        Log.d("ProyectoOrganizador", "initRecyclerView: $projectList")
    }
}