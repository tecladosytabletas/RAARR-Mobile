package com.example.appatemporal.framework.view.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.databinding.ProyectosOrganizadorItemBinding

class ProjectsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ProyectosOrganizadorItemBinding.bind(view)

    fun render(projectModel: Proyecto){
        binding.tvProjectName.text = projectModel.nombre_proyecto

    }


}