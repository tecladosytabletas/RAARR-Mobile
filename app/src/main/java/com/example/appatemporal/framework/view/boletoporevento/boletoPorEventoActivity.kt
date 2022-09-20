package com.example.test_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.test_02.adapter.boletosPorEventoAdapter

class boletoPorEventoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boleto_por_evento_activity)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.boletosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) // Le da el layout que usará el RV.
        recyclerView.adapter = boletosPorEventoAdapter.proyectoAdapter(boletosPorEventoProvider.proyectoList)
    }

}