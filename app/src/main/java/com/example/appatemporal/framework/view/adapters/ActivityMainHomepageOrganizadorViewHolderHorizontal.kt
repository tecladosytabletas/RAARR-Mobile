package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosChicaEspectadorBinding
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosGrandeEspectadorBinding
import com.example.appatemporal.domain.models.EventModel
import com.example.appatemporal.domain.models.EventsInMonth
import com.example.appatemporal.framework.view.ActivityVisualizarEventoEspectador
import com.example.appatemporal.framework.view.ActivityVisualizarEventoOrganizador
import com.squareup.picasso.Picasso
import com.example.appatemporal.framework.view.ConsultarBoleto

class ActivityMainHomepageOrganizadorViewHolderHorizontal(view: View) : RecyclerView.ViewHolder(view){
    val binding = ActivityHomepageTarjetaEventosChicaEspectadorBinding.bind(view)

    fun render(eventModel: EventModel){
        binding.nombreArtista.text = eventModel.nombre
        binding.fecha.text = eventModel.ubicacion
        binding.lugar.text = eventModel.direccion
        Picasso.get().load(eventModel.foto_portada).into(binding.imagenSmallCard)

        var cardEventBtn = binding.imagenSmallCard

        cardEventBtn.setOnClickListener {
            var idEvent : String = eventModel.id

            val eventoIndividual =  Intent(itemView.context, ActivityVisualizarEventoEspectador::class.java)

            eventoIndividual.putExtra("idEvent", idEvent)
        }
    }

}