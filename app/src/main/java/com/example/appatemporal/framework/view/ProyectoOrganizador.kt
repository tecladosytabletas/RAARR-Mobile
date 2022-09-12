package com.example.appatemporal.framework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel

class ProyectoOrganizador : AppCompatActivity() {
    private val  viewModel : ProyectoOrganizadorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.proyectos_organizador)
        val newProjectButton = findViewById<TextView>(R.id.tvNewProject)
        val repository = Repository(this)
        lateinit var projects : List<Proyecto>
        lifecycleScope.launchWhenStarted {
            projects = viewModel.getProjects(repository)
        }



        newProjectButton.setOnClickListener {
            val intent = Intent(this, AddNewProjectForm::class.java)
            startActivity(intent)
        }
    }
}