package com.example.appatemporal.framework.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Proyecto


class ProjectsAdapter: RecyclerView.Adapter<ProjectsAdapter.MyViewHolder>()  {
    // Generate a proyecto_organizador_item for each project
    private var projectsList = emptyList<Proyecto>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val projectName = itemView.findViewById<TextView>(R.id.tvProjectName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.proyectos_organizador_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = projectsList[position]
        holder.projectName.text = currentItem.nombre_proyecto

    }

    override fun getItemCount(): Int {
        return projectsList.size
    }

    fun setData(proyecto: List<Proyecto>){
        this.projectsList = proyecto
        notifyDataSetChanged()
    }





}