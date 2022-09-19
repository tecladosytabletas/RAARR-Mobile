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
            val tsLong = System.currentTimeMillis() / 1000
            val ts: String = tsLong.toString()
            //val project: Proyecto = Proyecto(idproject, 1, name, date,0.0,0.0,ts)
            lifecycleScope.launch{
                viewModel.updateModify(name,date,ts,idproject, repository)
            }
            // Go back to main activity
            val intent = Intent(this, ProyectoOrganizador::class.java)
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