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
    private lateinit var binding: ActivityBoletoPorEventoBinding
    private val getTicketViewModel : GetUserTicketViewModel by viewModels()
    private val repository = Repository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoletoPorEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTicketViewModel.getUserTicket("pod6xLDUeRNZItm7u93DC5CYbgJ2", repository)
        getTicketViewModel.ticket.observe(this, Observer { value ->

        })

        //getTicketViewModel.getUserTicket("x02BQ0RcJmRjhsvYJJ9z", "12hEWP8xQQgQGjCyuWon","UUX75bE59gTT0RBOqHLB", repository)
        //getTicketViewModel.ticket.observe(this, Observer { value ->
        //    hash_qr = value.toString()
        //})

        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.boletosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) // Le da el layout que usará el RV.
        recyclerView.adapter = boletosPorEventoAdapter.proyectoAdapter(boletosPorEventoProvider.proyectoList)
    }

}