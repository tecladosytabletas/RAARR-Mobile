package com.example.appatemporal.framework.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.framework.view.homepageorganizador.recyclerview.AdapterRVHorizontal
import com.example.appatemporal.framework.view.homepageorganizador.recyclerview.AdapterRVVertical
import com.example.appatemporal.framework.view.homepageespectador.TarjetaChica_espectador
import com.example.appatemporal.framework.view.homepageespectador.TarjetaGrande_espectador

class activity_visualizarevento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visualizar_evento)
    }

}