package com.example.appatemporal.framework.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class Dashboard : AppCompatActivity(){
    private lateinit var binding : DashboardBinding
    private val dashboardViewModel : DashboardViewModel by viewModels()
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

        val tempUserId : String = "HWRTS0ZBbnk8IffKtrNx"
        repository = Repository(this)

        //Inicia llamado a funciones
        //Grafica1 - Ganancias totales y cantidad de eventos totales
        populateEventCount(tempUserId)
        //Grafica2 - Asistentes esperados totales vs. Asistentes reales totales
        var ventasTotal : Int = 0
        var asistenciasTotal : Int = 0
        dashboardViewModel.ventasEvent(tempUserId, repository)
        dashboardViewModel.ventas.observe(this, Observer{
            ventasTotal = it.first
            asistenciasTotal = it.second
            populatePieChart(ventasTotal, asistenciasTotal)
        })
        //Grafica3 - Rating promedio de todos los eventos
        populateRating(tempUserId)
    }

    private fun populateEventCount(uid:String) {
        val ourRevenue = binding.eventRevenue
        val ourEventCount = binding.eventCountTotal
        repository = Repository(this)
        dashboardViewModel.countEvent(uid, repository)
        dashboardViewModel.count.observe(this, Observer{
            ourEventCount.text = "En ${it} eventos"
        })
        dashboardViewModel.getRevenue(uid, repository)
        dashboardViewModel.revenue.observe(this, Observer{
            val profit: String = NumberFormat.getNumberInstance(Locale.US).format(500000)
            ourRevenue.text = "$${profit} MXN"
        })
    }

    private fun populatePieChart(ventasTotal: Int, asistenciasTotal : Int) {
        //ingreso de los datos a la pie chart
        val ourPieChart = binding.dashPieChart
        val ourPieEntry = ArrayList<PieEntry>()
        var noAssist = ventasTotal - asistenciasTotal
        ourPieEntry.add(PieEntry(noAssist.toFloat(), "No Asistieron"))
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

    private fun populateRating(uid:String){
        val ourRatingBar = binding.ratingStars
        val ourRatingValue = binding.ratingAvg
        repository = Repository(this)
        dashboardViewModel.getRating(uid, repository)
        dashboardViewModel.rating.observe(this, Observer{
            ourRatingBar.rating = it
            ourRatingValue.text = "Rating promedio de ${it}"
        })
    }
}