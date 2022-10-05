package com.example.appatemporal.framework.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.domain.models.EventsInMonth

class ActivityMainHomepageEspectadorAdapter(private val list: MutableList<EventsInMonth>) : RecyclerView.Adapter<ActivityMainHomepageEspectadorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityMainHomepageEspectadorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActivityMainHomepageEspectadorViewHolder(layoutInflater.inflate(R.layout.activity_homepage_tarjeta_eventos_grande_espectador, parent, false))
    }

    override fun onBindViewHolder(holder: ActivityMainHomepageEspectadorViewHolder, position: Int) {
        val item = list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = list.size
}