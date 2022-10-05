package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosChicaEspectadorBinding
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosGrandeEspectadorBinding
import com.example.appatemporal.domain.models.EventsInMonth
import com.example.appatemporal.framework.view.ActivityVisualizarEventoOrganizador
import com.squareup.picasso.Picasso
import com.example.appatemporal.framework.view.ConsultarBoleto

class ActivityMainHomepageEspectadorViewHolderHorizontal(view: View) : RecyclerView.ViewHolder(view){
    val binding = ActivityHomepageTarjetaEventosChicaEspectadorBinding.bind(view)

    fun render(eventsInMonth: EventsInMonth){
        binding.nombreArtista.text = eventsInMonth.nombreEvento
        binding.fecha.text = eventsInMonth.lugarEvento
        binding.lugar.text = eventsInMonth.horaInicioEvento
        Picasso.get().load(eventsInMonth.urlImagen).into(binding.imagenSmallCard)

        var cardEventBtn = binding.imagenSmallCard

        cardEventBtn.setOnClickListener {
            var idEvent : String = eventsInMonth.idEvent

            val eventoIndividual =  Intent(itemView.context, ActivityVisualizarEventoOrganizador::class.java)

            eventoIndividual.putExtra("idEvent", idEvent)
        }
    }

}