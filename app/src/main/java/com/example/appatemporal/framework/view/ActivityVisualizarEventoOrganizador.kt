package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityVisualizarEventoOrganizadorBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.GraphicsEventDetailViewModel

class ActivityVisualizarEventoOrganizador : AppCompatActivity() {

    private lateinit var binding : ActivityVisualizarEventoOrganizadorBinding
    private val graphicsEventDetailViewModel : GraphicsEventDetailViewModel by viewModels()
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_evento_organizador)

        binding = ActivityVisualizarEventoOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val tempEventId : String = "DM"
        populateRating(tempEventId)
    }

    fun populateRating(eid:String){
        //Rating general del evento
        val ourRatingBar = binding.ratingStar
        val ourRatingValue = binding.ratingAvg
        val ourRatingCount = binding.ratingQuantity
        //Barra de 5 estrellas
        val ourRatingCount5 = binding.count5PB
        val ourRating5 = binding.count5TV
        //Declaración del repositorio
        repository = Repository(this)
        //Llamada a la función y al observador para modificar elementos de Rating
        graphicsEventDetailViewModel.getExtEventRating(eid, repository)
        graphicsEventDetailViewModel.ratingExt.observe(this, Observer{
            ourRatingBar.rating = it[7]
            ourRatingValue.text = "${it[7]} de 5"
            ourRatingCount.text =  "${it[1].toInt()} calificaciones"
            if(it[6]>0){
                ourRatingCount5.progress = ((it[6]*100)/it[1]).toInt()
                ourRating5.text = "${it[6].toInt()} votos"
            }else{
                ourRatingCount5.progress = 0
                ourRating5.text = "0 votos"
            }
        })
    }

}