package com.example.appatemporal.framework.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.CategoriasEventosItemBinding
import com.example.appatemporal.domain.models.CategoryModel
import com.example.appatemporal.domain.models.EventModel

class EventosFiltradosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CategoriasEventosItemBinding.bind(view)

    fun bind(evento: EventModel) {
        binding.tvCategoria.text = evento.nombre
    }

}