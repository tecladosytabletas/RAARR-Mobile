package com.example.appatemporal.framework.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R

class boletosPorEventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titulo_boleto = view.findViewById<TextView>(R.id.Nombre)
    val fecha_boleto = view.findViewById<TextView>(R.id.Fecha)
    val hora_boleto = view.findViewById<TextView>(R.id.Hora)

    fun render(boletoClass: boletoPorEventoClass){
        titulo_boleto.text = boletoClass.boletoTitulo
        fecha_boleto.text = boletoClass.boletoFecha
        hora_boleto.text = boletoClass.boletoHora
    }
}