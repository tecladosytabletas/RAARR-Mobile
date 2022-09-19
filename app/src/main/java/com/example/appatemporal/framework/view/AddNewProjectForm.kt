package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.entities.Objetivo
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.databinding.CreateNewProjectBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.AddNewProjectViewModel
import kotlinx.coroutines.launch

class AddNewProjectForm : AppCompatActivity() {
    private val viewModel: AddNewProjectViewModel by viewModels()
    private lateinit var binding : CreateNewProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateNewProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)

        // Set click listener
        binding.button.setOnClickListener {
            // Get values from view
            val name = binding.nameCreateNewProject.text.toString()
            val date = binding.dateCreateNewProject.text.toString()
            val tsLong = System.currentTimeMillis() / 1000
            val ts: String = tsLong.toString()
            val project: Proyecto = Proyecto(0, 1, name, date,0.0,0.0, 0.0,ts)

            lifecycleScope.launch{
                viewModel.addNewProject(project, repository)
            }

            // Go back to main activity
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)

        }

    }

}