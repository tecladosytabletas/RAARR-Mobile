package com.example.appatemporal.framework.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.CategoriasEventosItemBinding
import com.example.appatemporal.domain.models.CategoryModel

class CategoriasEventosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CategoriasEventosItemBinding.bind(view)

    fun bind(categoria: CategoryModel) {
        binding.tvCategoria.text = categoria.nombre
    }

}