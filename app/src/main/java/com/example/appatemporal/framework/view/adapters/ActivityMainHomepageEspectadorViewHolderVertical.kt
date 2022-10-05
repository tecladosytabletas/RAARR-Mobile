package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosChicaEspectadorBinding
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosGrandeEspectadorBinding
import com.example.appatemporal.domain.models.EventsInMonth
import com.example.appatemporal.framework.view.ActivityVisualizarEventoOrganizador
import com.example.appatemporal.framework.view.ConsultarBoleto
import com.squareup.picasso.Picasso

class ActivityMainHomepageEspectadorViewHolderVertical(view: View) : RecyclerView.ViewHolder(view){
    val binding = ActivityHomepageTarjetaEventosGrandeEspectadorBinding.bind(view)

    fun render(eventsInMonth: EventsInMonth){
        binding.Nombre.text = eventsInMonth.nombreEvento
        binding.Lugar.text = eventsInMonth.lugarEvento
        binding.Direccion.text = eventsInMonth.descEvento
        Picasso.get().load(eventsInMonth.imagen).into(binding.imageCard)

        var cardEventBtn = binding.cardEvent

        cardEventBtn.setOnClickListener {
            var idEvent : String = eventsInMonth.idEvent

            val eventoIndividual =  Intent(itemView.context, ActivityVisualizarEventoOrganizador::class.java)

            eventoIndividual.putExtra("idEvent", idEvent)
        }
    }

}