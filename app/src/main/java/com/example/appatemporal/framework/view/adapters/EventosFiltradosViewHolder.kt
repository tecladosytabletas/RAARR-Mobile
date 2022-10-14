package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.ActivityHomepageTarjetasEventosGrandeBinding
import com.example.appatemporal.databinding.CategoriasEventosItemBinding
import com.example.appatemporal.domain.models.CategoryModel
import com.example.appatemporal.domain.models.EventModel
import com.example.appatemporal.framework.view.ActivityVisualizarEventoEspectador
import com.squareup.picasso.Picasso

class EventosFiltradosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ActivityHomepageTarjetasEventosGrandeBinding.bind(view)

    fun bind(evento: EventModel) {

        binding.nombreTarjetaGrande.text = evento.nombre
        binding.fechaTarjetaGrande.text = evento.ubicacion
        binding.descripcionTarjetaGrande.text = evento.descripcion
        Picasso.get().load(evento.foto_portada).into(binding.imageView)

        var cardEventBtn = binding.card

        cardEventBtn.setOnClickListener {
            var idEvent : String = evento.id
            var nombre : String = evento.nombre
            var direccion : String = evento.direccion
            var estado :String = evento.ciudad+", "+evento.estado
            var ubicacion :String = evento.ubicacion
            var foto_portada : String = evento.foto_portada


            val eventoIndividual =  Intent(itemView.context, ActivityVisualizarEventoEspectador::class.java)

            eventoIndividual.putExtra("idEvent", idEvent)
            eventoIndividual.putExtra("nombre", nombre)
            eventoIndividual.putExtra("direccion", direccion)
            eventoIndividual.putExtra("estado", estado)
            eventoIndividual.putExtra("ubicacion", ubicacion)
            eventoIndividual.putExtra("foto_portada", foto_portada)

            itemView.context.startActivity(eventoIndividual)
        }
    }

}