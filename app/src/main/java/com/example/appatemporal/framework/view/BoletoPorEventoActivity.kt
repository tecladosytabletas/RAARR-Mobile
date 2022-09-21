package com.example.appatemporal.framework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityBoletoPorEventoBinding

class BoletoPorEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoletoPorEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoletoPorEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.boletosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) // Le da el layout que usará el RV.
        recyclerView.adapter = boletosPorEventoAdapter.proyectoAdapter(boletosPorEventoProvider.proyectoList)
    }

}