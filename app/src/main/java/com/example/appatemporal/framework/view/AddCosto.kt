package com.example.appatemporal.framework.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivityProyectoOrganizadorBinding
/**
 * This file is linked with add_costos.xml
 * This file is in charge of controlling the logic behind the funtion add a costo
 *
 * */
class AddCosto : AppCompatActivity() {
    private lateinit var binding: ActivityProyectoOrganizadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProyectoOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myExtras : Bundle? = intent.extras

        binding.projectName.text = myExtras?.getString("nombre_costo")
    }

}