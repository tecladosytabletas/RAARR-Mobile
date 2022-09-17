package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.databinding.ModifyNewProjectBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.DeleteActivityViewModel
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import kotlinx.coroutines.launch

class ModificarActividad : AppCompatActivity(){
    private val  viewModel : DeleteActivityViewModel by viewModels()
    private lateinit var binding: ModifyNewActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyNewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        var myExtras :Bundle? = intent.extras
        binding.nameModifyActivity.setText(myExtras?.getString("nombre_actividad"))
        val idactividad: Int = myExtras?.getInt("id_actividad")?:-1
        // Set click listener
        binding.modifybutton.setOnClickListener {
            val name = binding.nameModifyActivity.text.toString()
            val actividad: Actividad = Actividad(idactividad, name)
            lifecycleScope.launch{
                viewModel.updateActividad(actividad, repository)
            }
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

}