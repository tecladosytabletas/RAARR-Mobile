package com.example.appatemporal.framework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityBoletoPorEventoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.GetUserTicketViewModel

class BoletoPorEventoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val getTicketViewModel : GetUserTicketViewModel by viewModels()
         val repository = Repository(this)
        super.onCreate(savedInstanceState)
        var binding: ActivityBoletoPorEventoBinding = ActivityBoletoPorEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userIdTemp = "pod6xLDUeRNZItm7u93DC5CYbgJ2"

        val recyclerView = findViewById<RecyclerView>(R.id.boletosRecyclerView)
        var adapter = boletosPorEventoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this) // Le da el layout que usará el RV.
        // recyclerView.adapter = boletosPorEventoAdapter.proyectoAdapter(boletosPorEventoProvider.proyectoList)
        recyclerView.adapter = adapter

        initRecyclerView(getTicketViewModel, userIdTemp, repository)
    }

    private fun initRecyclerView(getTicketViewModel: GetUserTicketViewModel, userIdTemp: String, repository: Repository){
        getTicketViewModel.getUserTicket(userIdTemp, repository)
        getTicketViewModel.ticket.observe(this, Observer {
            ada
        })
    }

}