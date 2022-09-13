package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.databinding.ProyectosOrganizadorItemBinding
import com.example.appatemporal.framework.view.ActivityProyectoOrganizador
import com.example.appatemporal.framework.view.dialogFragment.DeleteDialogFragment

class ProjectsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ProyectosOrganizadorItemBinding.bind(view)

    fun render(projectModel: Proyecto){
        binding.tvProjectName.text = projectModel.nombre_proyecto
        binding.tvProjectName.setOnClickListener{
            val intent = Intent(itemView.context, ActivityProyectoOrganizador::class.java)
            with(intent){
                putExtra("id_proyecto", projectModel.id_proyecto)
                putExtra("nombre_proyecto", projectModel.nombre_proyecto)
                putExtra("fecha_inicio", projectModel.fecha_inicio)
            }
            itemView.context.startActivity(intent)
        }

        binding.deleteProjectButton.setOnClickListener{
            val intent = Intent(itemView.context, DeleteDialogFragment::class.java)
            with(intent){
                putExtra("id_proyecto", projectModel.id_proyecto)
                putExtra("nombre_proyecto", projectModel.nombre_proyecto)
                putExtra("fecha_inicio", projectModel.fecha_inicio)
            }
            itemView.context.startActivity(intent)
        }
    }
}