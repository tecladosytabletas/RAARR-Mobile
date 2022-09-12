package com.example.appatemporal.framework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ProjectsAdapter
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProyectoOrganizador : AppCompatActivity() {
    private val  viewModel : ProyectoOrganizadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.proyectos_organizador)
        val repository = Repository(this)
        val newProjectButton = findViewById<TextView>(R.id.tvNewProject)
        lifecycleScope .launch {
            initRecyclerView(repository)
        }



        newProjectButton.setOnClickListener {
            val intent = Intent(this, AddNewProjectForm::class.java)
            startActivity(intent)
        }
    }

    private suspend fun initRecyclerView(repository: Repository) {
        val projectList = viewModel.getProjects(repository)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProjectsAdapter(projectList)
        Log.d("ProyectoOrganizador", "initRecyclerView: $projectList")



    }

}