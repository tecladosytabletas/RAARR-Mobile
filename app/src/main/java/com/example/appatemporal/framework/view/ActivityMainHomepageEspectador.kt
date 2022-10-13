package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.ActivityMainHomepageEspectadorBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ActivityMainHomepageEspectadorAdapterHorizontal
import com.example.appatemporal.framework.view.adapters.ActivityMainHomepageEspectadorAdapterVertical
import com.example.appatemporal.framework.viewModel.GetEventsInMonthViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.*

/**
 * This file is linked with activity_main_homepage_organizador.xml
 * This file is in charge of displaying the events in different cards of Recycler Views
 *
 * @see activity_main_homepage_organizador.xml
 *
 * @author Andrés & Aldo
 *
 * */

class ActivityMainHomepageEspectador : AppCompatActivity() {
    private lateinit var binding: ActivityMainHomepageEspectadorBinding
    val getEventsInMonthViewModel : GetEventsInMonthViewModel by viewModels()
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainHomepageEspectadorBinding.inflate(layoutInflater)
        val userRole = getSharedPreferences("user", Context.MODE_PRIVATE).getString("rol", "").toString()
        setContentView(binding.root)
        if(userRole == "Organizador"){
            val intent = Intent(this, ActivityMainHomepageOrganizador::class.java)
            startActivity(intent)
        }

        else if(userRole == "Ayudante"){
            val intent = Intent(this, RegisterQRView::class.java)
            startActivity(intent)
        }


        val repository = Repository(this)

        val dia = getDay()
        val month = getMonth()
        val year = getYear()

        initRecyclerViewHorizontal(getEventsInMonthViewModel, dia, month, year ,repository)
        initRecyclerViewVertical(getEventsInMonthViewModel, repository)


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

    fun getDay() : Int {
        val cal = Calendar.getInstance()
        val dayOfMonth =  cal[Calendar.DAY_OF_MONTH]
        // Log.d("pruebasDia-Actual",dayOfMonth.toString())
        return dayOfMonth
    }

    fun getMonth() : Int {
        val cal = Calendar.getInstance()
        val numberOfMonth =  cal[Calendar.MONTH]+1
        // Log.d("pruebasMes-Actual",numberOfMonth.toString())
        return numberOfMonth
    }

    fun getYear() : Int {
        val cal = Calendar.getInstance()
        val year =  cal[Calendar.YEAR]
        //Log.d("pruebasAño-Actual",year.toString())
        return year
    }

    private fun initRecyclerViewHorizontal(getEventsInMonthViewModel: GetEventsInMonthViewModel, day: Int, month: Int, year: Int, repository: Repository){
        getEventsInMonthViewModel.getEventsMonth(day, month, year, repository)
        //Log.d("LOG Activity",getEventsInMonthViewModel.getEventsMonth(day, month, year, repository).toString())
        getEventsInMonthViewModel.eventsMonth.observe(this, Observer { eventsList ->
            val linearLayout = LinearLayoutManager(this)
            linearLayout.orientation=LinearLayoutManager.HORIZONTAL
            binding.HorizontalEspectadorView.layoutManager = linearLayout // Le da el layout que usará el RV.
            binding.HorizontalEspectadorView.adapter = ActivityMainHomepageEspectadorAdapterHorizontal(eventsList)

        })
    }

    private fun initRecyclerViewVertical(getEventsInMonthViewModel: GetEventsInMonthViewModel, repository: Repository){
        getEventsInMonthViewModel.getAllEvents(repository)
        //Log.d("LOG Activity",getEventsInMonthViewModel.getEventsMonth(day, month, year, repository).toString())
        getEventsInMonthViewModel.allEvents.observe(this, Observer { eventsList ->
            val linearLayout = LinearLayoutManager(this)
            linearLayout.orientation=LinearLayoutManager.VERTICAL
            binding.VerticalView.layoutManager = linearLayout // Le da el layout que usará el RV.
            binding.VerticalView.adapter = ActivityMainHomepageEspectadorAdapterVertical(eventsList)
        })
    }
}