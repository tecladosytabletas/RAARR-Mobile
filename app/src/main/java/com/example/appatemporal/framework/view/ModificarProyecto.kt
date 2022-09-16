package com.example.appatemporal.framework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appatemporal.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.entities.Estatus
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.databinding.CreateNewProjectBinding
import com.example.appatemporal.databinding.ModifyNewProjectBinding
import com.example.appatemporal.databinding.VistaModificarproyectoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.AddNewProjectViewModel
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import kotlinx.coroutines.launch

class ModificarProyecto : AppCompatActivity() {

    private val viewModel: ProyectoOrganizadorViewModel by viewModels()
    private lateinit var binding : ModifyNewProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyNewProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        var myExtras :Bundle? = intent.extras
        binding.nameModifyProject.setText(myExtras?.getString("nombre_proyecto"))
        binding.dateModifyProject.setText(myExtras?.getString("fecha_inicio"))
        val idproject: Int = myExtras?.getInt("id_proyecto")?:-1
        // Set click listener
        binding.modifybutton.setOnClickListener {
            // Get values from view
            val name = binding.nameModifyProject.text.toString()
            val date = binding.dateModifyProject.text.toString()

            //
            //val id1 = 1
            //val estado1 = "Completado"
            //val id = 2
            //val estado = "No Completado"
            //val estatus1: Estatus = Estatus(id1, estado1, date, date)
            //val estatus2: Estatus = Estatus(id, estado, date, date)
            val project: Proyecto = Proyecto(idproject, 1, name, date)
            lifecycleScope.launch{
                //viewModel.inserEstatus(repository, estatus1)
                //viewModel.inserEstatus(repository, estatus2)
                viewModel.updateProject(project, repository)
            }
            // Go back to main activity
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)

        }

    }

}