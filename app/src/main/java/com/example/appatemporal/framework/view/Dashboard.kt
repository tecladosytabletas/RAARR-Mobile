package com.example.appatemporal.framework.view

import android.content.Intent
import android.graphics.Color
import android.media.Rating
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.appatemporal.R
import com.example.appatemporal.data.network.dataclasses.DashPieModel
import com.example.appatemporal.databinding.ActivityActividadesOrganizadorBinding.inflate
import com.example.appatemporal.databinding.DashboardBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.CountEventViewModel
import com.example.appatemporal.framework.viewModel.GetRatingViewModel
import com.example.appatemporal.framework.viewModel.GetRevenueViewModel
import com.example.appatemporal.framework.viewModel.VentasCountViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.dashboard.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class Dashboard : AppCompatActivity(){
    private lateinit var ourPieChart: PieChart
    private lateinit var ourEventCount : TextView
    private lateinit var ourEventRevenue : TextView
    private lateinit var eventCountTotal : String
    private lateinit var binding : DashboardBinding
    private val countEventViewModel : CountEventViewModel by viewModels()
    private val ventasCountViewModel : VentasCountViewModel by viewModels()
    private val getRatingViewModel : GetRatingViewModel by viewModels()
    private val getRevenueViewModel : GetRevenueViewModel by viewModels()
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        // En el onCreate se deben poblar las graficas
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        binding = DashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navbar.homeIcon.setOnClickListener {
            finish()
        }

        binding.navbar.budgetIcon.setOnClickListener {
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

        binding.navbar.ticketsIcon.setOnClickListener {
            finish()
        }

        binding.navbar.metricsIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }



        // Declaraciom de datos dinamicos
        ourEventCount = findViewById(R.id.eventCountTotal)
        ourEventRevenue = findViewById(R.id.eventCount)
        ourPieChart = findViewById(R.id.dashPieChart)

        val tempUserId : String = "HWRTS0ZBbnk8IffKtrNx"
        repository = Repository(this)
        var ventasTotal : Int = 0
        var asistenciasTotal : Int = 0

        ventasCountViewModel.ventasEvent(tempUserId, repository)
        ventasCountViewModel.ventas.observe(this, Observer{
            ventasTotal = it.first
            asistenciasTotal = it.second
            populatePieChart(ventasTotal, asistenciasTotal)
        })

        populateEventCount()
        populateRating()
    }

    // Ejemplo de poblar ingresos de evento

    private fun populateEventCount() {

        // Aqui debe de recuperar los datos de Firebase y asignarlos a la variable eventCountEntry

        var eventRevenueEntry = "REVENUE"
        val tempUserId : String = "HWRTS0ZBbnk8IffKtrNx"
        val ourRevenue = binding.eventCount

        repository = Repository(this)
        countEventViewModel.countEvent(tempUserId, repository)
        getRevenueViewModel.getRevenue(tempUserId, repository)

        //ourEventRevenue.text = "$ ${eventRevenueEntry} MXN"

        countEventViewModel.count.observe(this, Observer{
            eventCountTotal = it.toString()
            ourEventCount.text = "En ${eventCountTotal} eventos"
        })

        getRevenueViewModel.revenue.observe(this, Observer{
            ourRevenue.text = "$ ${it} MXN"
        })
    }

    // Ejemplo de poblar la grafica de pastel

    private fun populatePieChart(ventasTotal: Int, asistenciasTotal : Int) {
        // Aqui se reciben los datos en teoria
        val ourPieEntry = ArrayList<PieEntry>()
        var noAssist = ventasTotal - asistenciasTotal

        ourPieEntry.add(PieEntry(noAssist.toFloat(), "No Asistieron"))
        ourPieEntry.add(PieEntry(asistenciasTotal.toFloat(), "Asistieron"))

        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)

        // De aqui para abajo es formato

        val pieShades: ArrayList<Int> = ArrayList()
        pieShades.add(Color.parseColor("#FFBB86FC"))
        pieShades.add(Color.parseColor("#FE810E"))

        ourSet.colors = pieShades
        ourPieChart.data = data
        ourPieChart.invalidate()

        data.setValueTextColor(Color.DKGRAY)
        data.setValueTextSize(20f)

        ourPieChart.getLegend().setTextColor(Color.DKGRAY)
        ourPieChart.getDescription().setTextColor(Color.DKGRAY)
        ourPieChart.setEntryLabelColor(Color.DKGRAY)

        ourPieChart.description.isEnabled = false
        ourPieChart.setDrawEntryLabels(false)
    }

    private fun populateRating(){
        val ourRatingBar = binding.ratingStars
        val ourRatingValue = binding.ratingAvg
        repository = Repository(this)
        val tempUserId : String = "HWRTS0ZBbnk8IffKtrNx"

        getRatingViewModel.getRating(tempUserId, repository)
        getRatingViewModel.rating.observe(this, Observer{
            ourRatingBar.rating = it
            ourRatingValue.text = "Rating promedio de ${it}"
        })
    }
}