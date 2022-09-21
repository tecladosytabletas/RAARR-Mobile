package com.example.appatemporal.framework.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityBoletoPorEventoBinding
import com.example.appatemporal.databinding.ActivityCardBoletoPorEventoBinding
import com.example.appatemporal.domain.models.TicketModel

class boletosPorEventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ActivityCardBoletoPorEventoBinding.bind(view)


    fun render(boletoClass: TicketModel){
        binding.Nombre.text = boletoClass.nombre_evento
        binding.Fecha.text = boletoClass.fecha
        binding.Hora.text = boletoClass.horario
    }
}