package com.example.appatemporal.framework.view.adapters

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.databinding.ProyectosOrganizadorItemBinding
import com.example.appatemporal.framework.view.ActivityProyectoOrganizador
import com.example.appatemporal.framework.view.ModificarProyecto
import com.example.appatemporal.framework.view.ProyectoOrganizador


class ProjectsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ProyectosOrganizadorItemBinding.bind(view)

    fun render(projectModel: Proyecto){
        binding.tvProjectName.text = projectModel.nombre_proyecto
        binding.ivEditIcon.setOnClickListener{
            val intent1 = Intent(itemView.context, ModificarProyecto::class.java)
            with(intent1){
                putExtra("id_proyecto", projectModel.id_proyecto)
                putExtra("nombre_proyecto", projectModel.nombre_proyecto)
                putExtra("fecha_inicio", projectModel.fecha_inicio)
            }
            itemView.context.startActivity(intent1)
        }
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
            val intent = Intent(itemView.context, ActivityProyectoOrganizador::class.java)

            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("¿Estás seguro?")
            builder.setMessage("¿Estás seguro de que quieres eliminar este proyecto? Este proceso no puede revertirse")
            //builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Eliminar"){dialogInterface, which ->
                //Mandar a llamar la funcion delete()
                Toast.makeText(itemView.context,"Se eliminó el proyecto correctamente",Toast.LENGTH_LONG).show()
            }
            builder.setNeutralButton("Cancelar"){dialogInterface , which ->

            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}