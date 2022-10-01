package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.CategoriasEventosItemBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.CategoryModel
import com.example.appatemporal.framework.view.ActivityProyectoOrganizador
import com.example.appatemporal.framework.view.EventosFiltrados
import com.example.appatemporal.framework.viewModel.CategoriasEventosViewModel
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel

class CategoriasEventosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CategoriasEventosItemBinding.bind(view)

    fun bind(categoria: CategoryModel) {
        binding.tvCategoria.text = categoria.nombre

        binding.tvCategoria.setOnClickListener{
            val intent = Intent(itemView.context, EventosFiltrados::class.java)
            with(intent){
                putExtra("categoria_nombre", categoria.nombre)
            }
            itemView.context.startActivity(intent)
        }
    }


}