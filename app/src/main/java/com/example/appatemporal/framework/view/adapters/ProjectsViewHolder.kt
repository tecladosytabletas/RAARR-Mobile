package com.example.appatemporal.framework.view.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Proyecto

class ProjectsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val project: TextView = view.findViewById<TextView>(R.id.tvProjectName)
    fun render(projectModel: Proyecto){
        project.text = projectModel.nombre_proyecto

    }


}