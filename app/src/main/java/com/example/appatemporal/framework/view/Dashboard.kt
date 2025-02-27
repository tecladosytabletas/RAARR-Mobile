package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.appatemporal.R
import com.example.appatemporal.databinding.DashboardBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.*
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.google.firebase.auth.FirebaseAuth
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Class that inherits from AppCompatActivity
 */

class Dashboard : AppCompatActivity(){
    private lateinit var binding : DashboardBinding
    private val dashboardViewModel : DashboardViewModel by viewModels()
    private var auth = FirebaseAuth.getInstance()
    private lateinit var repository: Repository

    /**
     * Overrides function onCreate and starts the activity
     *
     * @param savedInstanceState: Bundle? -> Saved instance of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // En el onCreate se deben poblar las graficas
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        binding = DashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val uid = getSharedPreferences("user", Context.MODE_PRIVATE)
            .getString("userUid", "").toString()
        repository = Repository(this)

        //Inicia llamado a funciones
        //Grafica1 - Ganancias totales y cantidad de eventos totales
        populateEventCount(uid)
        //Grafica2 - Asistentes esperados totales vs. Asistentes reales totales
        var ventasTotal : Int = 0
        var asistenciasTotal : Int = 0
        dashboardViewModel.ventasEvent(uid, repository)
        dashboardViewModel.ventas.observe(this, Observer{
            ventasTotal = it.first
            asistenciasTotal = it.second
            populatePieChart(ventasTotal, asistenciasTotal)
        })
        //Grafica3 - Rating promedio de todos los eventos
        populateRating(uid)
    }

    /**
     * Populates the EventCount metric
     *
     * @param uid: String
     */
    private fun populateEventCount(uid:String) {
        val ourRevenue = binding.eventRevenue
        val ourEventCount = binding.eventCountTotal
        repository = Repository(this)
        dashboardViewModel.getRevenue(uid, repository)
        dashboardViewModel.revenue.observe(this, Observer{
            val profit: String = NumberFormat.getNumberInstance(Locale.US).format(it)
            ourRevenue.text = "$${profit} MXN"
        })
        dashboardViewModel.countEvent(uid, repository)
        dashboardViewModel.count.observe(this, Observer {
            if (it == 1){
                ourEventCount.text = "En ${it} evento"
            } else {
                ourEventCount.text = "En ${it} eventos"
            }
        })
    }

    /**
     * Populates the PieChart metric and sets the format for
     * the MPChart element
     *
     * @param ventasTotal: Int
     * @param asistenciasTotal: Int
     */
    private fun populatePieChart(ventasTotal: Int, asistenciasTotal : Int) {
        //ingreso de los datos a la pie chart
        val ourPieChart = binding.dashPieChart
        val ourPieEntry = ArrayList<PieEntry>()
        var noAssist = ventasTotal - asistenciasTotal
        ourPieEntry.add(PieEntry(noAssist.toFloat(), "No asistieron"))
        ourPieEntry.add(PieEntry(asistenciasTotal.toFloat(), "Asistieron"))
        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)
        //formato de la pie chart
        val pieShades: ArrayList<Int> = ArrayList()
        pieShades.add(Color.parseColor("#FFBB86FC"))
        pieShades.add(Color.parseColor("#FE810E"))
        ourSet.colors = pieShades
        ourPieChart.data = data
        ourPieChart.data.setValueFormatter(DefaultValueFormatter(0))
        data.setValueTextColor(Color.DKGRAY)
        data.setValueTextSize(20f)
        ourPieChart.getLegend().setTextColor(Color.DKGRAY)
        ourPieChart.getDescription().setTextColor(Color.DKGRAY)
        ourPieChart.setEntryLabelColor(Color.DKGRAY)
        ourPieChart.description.isEnabled = false
        ourPieChart.setDrawEntryLabels(false)
        ourPieChart.invalidate()
    }

    /**
     * Populates the Rating metric
     *
     * @param uid: String
     */
    private fun populateRating(uid:String){
        val ourRatingBar = binding.ratingStars
        val ourRatingValue = binding.ratingAvg
        repository = Repository(this)
        dashboardViewModel.getRating(uid, repository)
        dashboardViewModel.rating.observe(this, Observer{
            ourRatingBar.rating = it
            ourRatingValue.text = "Rating promedio: ${it}"
        })
    }
}