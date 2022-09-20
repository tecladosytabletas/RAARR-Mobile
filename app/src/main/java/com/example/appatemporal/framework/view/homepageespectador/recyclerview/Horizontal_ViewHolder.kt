package com.example.appatemporal.framework.view.homepageespectador.recyclerview

import android.system.Os.bind
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.databinding.HomepageTarjetaEventosChicaEspectadorBinding
import com.example.appatemporal.framework.view.homepageespectador.TarjetaChica_espectador


class Horizontal_ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = HomepageTarjetaEventosChicaEspectadorBinding.bind(view)
        fun bind(item: TarjetaChica_espectador){
            binding.lugar.text = item.lugar
            binding.fecha.text = item.fecha
            binding.nombreArtista.text = item.nombreArtista
        }

}
