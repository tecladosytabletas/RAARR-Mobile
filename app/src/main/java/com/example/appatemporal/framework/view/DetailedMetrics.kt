package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.appatemporal.R
import com.example.appatemporal.databinding.DetailedMetricsBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.GetEventNameViewModel

class DetailedMetrics : AppCompatActivity(){

    private lateinit var binding : DetailedMetricsBinding
    private lateinit var ourDashTitle : TextView
    private val eventNameViewModel : GetEventNameViewModel by viewModels()
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_metrics)

        binding = DetailedMetricsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Implementación de la NavBar
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
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        ourDashTitle = findViewById(R.id.dashTitle)

        setEventName()
    }

    private fun setEventName() {
        val tempEventId : String = "12hEWP8xQQgQGjCyuWon"
        repository = Repository(this)
        eventNameViewModel.getEventName(tempEventId,repository)
        eventNameViewModel.eventName.observe(this, Observer{
            ourDashTitle.text = "${it}"
        })
    }

}