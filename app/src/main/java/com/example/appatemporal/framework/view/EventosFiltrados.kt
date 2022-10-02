package com.example.appatemporal.framework.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.EventosFiltradosBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.EventosFiltradosAdapter
import com.example.appatemporal.framework.viewModel.EventosByCategoriasViewModel

class EventosFiltrados: AppCompatActivity() {
    private lateinit var binding: EventosFiltradosBinding
    private val viewModel : EventosByCategoriasViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EventosFiltradosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myExtras :Bundle? = intent.extras
        var categoria_nombre: String? = myExtras?.getString("categoria_nombre")
        val repository = Repository(this)
        viewModel.getEventsByCategory(repository, categoria_nombre.toString())
        viewModel.eventsByCategory.observe(this, Observer{ eventList ->
            Log.d("EventosFiltrados", "Eventos: $eventList")
            binding.RecyclerView.layoutManager = LinearLayoutManager(this)
            binding.RecyclerView.adapter = EventosFiltradosAdapter(eventList)
        })


    }


}