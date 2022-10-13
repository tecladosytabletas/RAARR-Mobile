package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.ActivityMainHomepageOrganizadorBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ActivityMainHomepageOrganizadorAdapterHorizontal
import com.example.appatemporal.framework.view.adapters.ActivityMainHomepageOrganizadorAdapterVertical
import com.example.appatemporal.framework.viewModel.GetEventsUserOrg
import com.google.firebase.auth.FirebaseAuth

/**
 * This file is linked with activity_main_homepage_espectador.xml
 * This file is in charge of displaying the events in different cards of Recycler Views
 *
 * @see activity_main_homepage_espectador.xml
 *
 * @author Andrés & Aldo
 *
 * */

class ActivityMainHomepageOrganizador : AppCompatActivity() {
    private lateinit var binding: ActivityMainHomepageOrganizadorBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        val getEventsUserOrg : GetEventsUserOrg by viewModels()
        val repository = Repository(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainHomepageOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userUid = getSharedPreferences("userUid", Context.MODE_PRIVATE).getString("userUid", "").toString()

        initRecyclerViewHorizontal(getEventsUserOrg, userUid, repository)
        initRecyclerViewVertical(getEventsUserOrg, repository)

        val userRole = getSharedPreferences("user", Context.MODE_PRIVATE).getString("rol", "").toString()
        // ----------------------------Header------------------------------------

        // Visibility

        if(auth.currentUser == null){
            binding.header.buttonsHeader.visibility = android.view.View.GONE
        }

        // Intents
        binding.header.logoutIcon.setOnClickListener{
            Log.d("Sesion", "Salió")
            auth.signOut()
            val userSharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)
            var sharedPrefEdit = userSharedPref.edit()
            sharedPrefEdit.remove("userUid")
            sharedPrefEdit.clear().apply()
            Log.d("Sesion", getSharedPreferences("user", Context.MODE_PRIVATE).getString("rol", "").toString())
            val intent = Intent(this, CheckIfLogged::class.java)
            startActivity(intent)
        }

        binding.header.supportIcon.setOnClickListener{
            val intent = Intent(this, SupportActivity::class.java)
            startActivity(intent)
        }
        // ----------------------------Navbar------------------------------------


        Log.d("Rol", userRole)

        // Visibility
        if ((userRole == "Espectador" && auth.currentUser != null) || userRole == "") {
            binding.navbar.budgetIcon.visibility = android.view.View.GONE
            binding.navbar.metricsIcon.visibility = android.view.View.GONE
            binding.navbar.budgetText.visibility = android.view.View.GONE
            binding.navbar.metricsText.visibility = android.view.View.GONE
        } else if (userRole == "Ayudante" && auth.currentUser != null) {
            binding.navbar.budgetIcon.visibility = android.view.View.GONE
            binding.navbar.metricsIcon.visibility = android.view.View.GONE
            binding.navbar.budgetText.visibility = android.view.View.GONE
            binding.navbar.metricsText.visibility = android.view.View.GONE
            binding.navbar.eventsIcon.visibility = android.view.View.GONE
            binding.navbar.eventsText.visibility = android.view.View.GONE
            binding.navbar.homeIcon.visibility = android.view.View.GONE
            binding.navbar.homeText.visibility = android.view.View.GONE
        }

        // Intents
        binding.navbar.homeIcon.setOnClickListener {
            if(userRole == "Organizador" && auth.currentUser != null){
                val intent = Intent(this, ActivityMainHomepageOrganizador::class.java)
                startActivity(intent)
            }else {
                val intent = Intent(this, ActivityMainHomepageEspectador::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.eventsIcon.setOnClickListener {
            if(userRole == "Organizador" && auth.currentUser != null) {
                val intent = Intent(this,ActivityMisEventosOrganizador::class.java)
                startActivity(intent)
            } else if (userRole == "Espectador" && auth.currentUser != null) {
                val intent = Intent(this,CategoriasEventos::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, CheckIfLogged::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.budgetIcon.setOnClickListener {
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

        binding.navbar.ticketsIcon.setOnClickListener {
            if ((userRole == "Espectador" || userRole == "Organizador") && auth.currentUser != null) {
                val intent = Intent(this, BoletoPorEventoActivity::class.java)
                startActivity(intent)
            } else if (userRole == "Ayudante" && auth.currentUser != null) {
                val intent = Intent(this, RegisterQRView::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, CheckIfLogged::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.metricsIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

    }

    private fun initRecyclerViewHorizontal(getEventsUserOrg: GetEventsUserOrg, userIdTemp: String, repository: Repository){
        getEventsUserOrg.getEventsOrg(userIdTemp, repository)
        // Log.d("LOG Activity",getEventsUserOrg.getEventsOrg(userIdTemp, repository).toString())
        getEventsUserOrg.allEventsOrg.observe(this, Observer { eventList ->
            val linearLayout = LinearLayoutManager(this)
            linearLayout.orientation=LinearLayoutManager.HORIZONTAL
            binding.HorizontalOrgView.layoutManager = linearLayout // Le da el layout que usará el RV.
            binding.HorizontalOrgView.adapter = ActivityMainHomepageOrganizadorAdapterHorizontal(eventList)
        })
    }

    private fun initRecyclerViewVertical(getEventsUserOrg: GetEventsUserOrg, repository: Repository){
        getEventsUserOrg.getAllEvents(repository)
        // Log.d("LOG Activity",getEventsInMonthViewModel.getAllEvents(repository).toString())
        getEventsUserOrg.allEvents.observe(this, Observer { eventList ->
            val linearLayout = LinearLayoutManager(this)
            linearLayout.orientation=LinearLayoutManager.VERTICAL
            binding.VerticalOrgView.layoutManager = linearLayout // Le da el layout que usará el RV.
            binding.VerticalOrgView.adapter = ActivityMainHomepageOrganizadorAdapterVertical(eventList)
        })
    }

}
