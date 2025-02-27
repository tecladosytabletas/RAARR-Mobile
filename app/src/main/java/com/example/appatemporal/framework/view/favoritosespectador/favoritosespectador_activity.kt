package com.example.lab

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.framework.view.favoritosespectador.TarjetaGrande_favoritosespectador
import com.example.appatemporal.framework.view.favoritosespectador.recyclerview.AdapterRVVertical

class favoritosespectador_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_favoritos_espectador)
        initRecyclerView()
    }
    @SuppressLint("WrongViewCast")
    private fun initRecyclerView(){
        val dataVertical: List<TarjetaGrande_favoritosespectador> = listOf(
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico"),
            TarjetaGrande_favoritosespectador("Luis miguel","22/12/2022","Mexico")
        )

        val recyclerViewVerticalFavoritosEspectador  = findViewById<RecyclerView>(R.id.RVFavoritosEspectador)
        val linearLayoutManager = LinearLayoutManager(this )
        linearLayoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerViewVerticalFavoritosEspectador.layoutManager = linearLayoutManager
        recyclerViewVerticalFavoritosEspectador.adapter = AdapterRVVertical(dataVertical)
    }
}