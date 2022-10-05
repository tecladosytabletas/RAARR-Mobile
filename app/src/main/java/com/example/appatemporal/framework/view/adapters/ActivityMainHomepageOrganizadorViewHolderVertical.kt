package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosChicaEspectadorBinding
import com.example.appatemporal.databinding.ActivityHomepageTarjetaEventosGrandeEspectadorBinding
import com.example.appatemporal.domain.models.EventModel
import com.example.appatemporal.domain.models.EventsInMonth
import com.example.appatemporal.framework.view.ActivityVisualizarEventoOrganizador
import com.example.appatemporal.framework.view.ConsultarBoleto
import com.squareup.picasso.Picasso

class ActivityMainHomepageOrganizadorViewHolderVertical(view: View) : RecyclerView.ViewHolder(view){
    val binding = ActivityHomepageTarjetaEventosGrandeEspectadorBinding.bind(view)

    fun render(eventModel: EventModel){
        binding.Nombre.text = eventModel.nombre
        binding.Lugar.text = eventModel.ubicacion
        binding.Direccion.text = eventModel.direccion
        Picasso.get().load(eventModel.foto_portada).into(binding.imageCard)

        var cardEventBtn = binding.cardEvent

        cardEventBtn.setOnClickListener {
            var idEvent : String = eventModel.id

            val eventoIndividual =  Intent(itemView.context, ActivityVisualizarEventoOrganizador::class.java)

            eventoIndividual.putExtra("idEvent", idEvent)
        }
    }

}