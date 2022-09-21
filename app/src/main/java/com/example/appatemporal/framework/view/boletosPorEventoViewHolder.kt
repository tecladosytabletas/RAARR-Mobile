package com.example.appatemporal.framework.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.domain.models.TicketModel

class boletosPorEventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titulo_boleto = view.findViewById<TextView>(R.id.Nombre)
    val fecha_boleto = view.findViewById<TextView>(R.id.Fecha)
    val hora_boleto = view.findViewById<TextView>(R.id.Hora)

    fun render(boletoClass: TicketModel){
        titulo_boleto.text = boletoClass.nombre_evento
        fecha_boleto.text = boletoClass.fecha
        hora_boleto.text = boletoClass.horario
    }
}