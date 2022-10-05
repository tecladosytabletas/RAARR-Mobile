package com.example.appatemporal.framework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityMainHomepageOrganizadorBinding
import com.example.appatemporal.databinding.ActivityVisualizareventoBinding
import com.example.appatemporal.domain.Repository

class ActivityVisualizarEventoEspectador : AppCompatActivity() {
    private lateinit var binding: ActivityVisualizareventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val repository = Repository(this)
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizareventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}