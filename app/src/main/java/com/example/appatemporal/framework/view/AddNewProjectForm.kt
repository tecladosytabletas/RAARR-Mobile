package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.AddNewProjectViewModel
import kotlinx.coroutines.launch

class AddNewProjectForm : AppCompatActivity() {
    private val viewModel: AddNewProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_project)

        val repository = Repository(this)

        // Bind all elements of view
        val projectName = findViewById<TextView>(R.id.nameCreateNewProject)
        val date = findViewById<TextView>(R.id.dateCreateNewProject)
        val submitButton = findViewById<Button>(R.id.button)


        // Set click listener
        submitButton.setOnClickListener {
            // Get values from view
            val name = projectName.text.toString()
            val date = date.text.toString()

            val project: Proyecto = Proyecto(0, 1, name, date)

            lifecycleScope.launch{
                viewModel.addNewProject(project, repository)
            }


            // Go back to main activity
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)

        }

    }

}