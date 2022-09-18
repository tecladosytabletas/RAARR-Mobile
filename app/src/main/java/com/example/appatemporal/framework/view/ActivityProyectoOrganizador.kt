package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Objetivo
import com.example.appatemporal.databinding.ActivityProyectoOrganizadorBinding
import com.example.appatemporal.domain.Repository
import kotlinx.coroutines.launch

class ActivityProyectoOrganizador: AppCompatActivity() {
    private lateinit var binding: ActivityProyectoOrganizadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProyectoOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myExtras :Bundle? = intent.extras

        binding.projectName.text = myExtras?.getString("nombre_proyecto")
        var idProyecto:Int =  myExtras?.getInt("id_proyecto")?:-1
        var ganancia:Int =  myExtras?.getInt("ganancia_proyecto")?:-1
        var presupuesto:Int =  myExtras?.getInt("presupuesto_proyecto")?:-1
        binding.bottomObjective.setOnClickListener {

            val intent = Intent(this, PresupuestoAndMeta::class.java)
            with(intent){
                putExtra("id_proyecto", idProyecto)
                putExtra("gananciaK", ganancia)
                putExtra("presupuestoK", presupuesto)
            }
            startActivity(intent)
        }
    }
}
