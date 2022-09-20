package com.example.appatemporal.framework.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.TextView
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
    private lateinit var countEventViewModel : CountEventViewModel
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        // En el onCreate se deben poblar las graficas
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        binding = DashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Declaraciom de datos dinamicos
        ourEventCount = findViewById(R.id.eventCountTotal)
        ourEventRevenue = findViewById(R.id.eventCount)
        ourPieChart = findViewById(R.id.dashPieChart)

        val tempUserId : String = "HWRTS0ZBbnk8IffKtrNx"

        countEventViewModel.countEvent(tempUserId, repository)


        populateEventCount()
        populatePieChart()

        activityTest()
    }

    // Ejemplo de poblar ingresos de evento

    private fun populateEventCount() {

        // Aqui debe de recuperar los datos de Firebase y asignarlos a la variable eventCountEntry

        val eventRevenueEntry = "REVENUE"

        //ourEventRevenue.text = "$ ${eventRevenueEntry} MXN"

        countEventViewModel.count.observe(this, Observer{
            eventCountTotal = it.toString()
        })

        ourEventCount.text = "En ${eventCountTotal} eventos"

    }

    // Ejemplo de poblar la grafica de pastel

    private fun populatePieChart() {
        // Aqui se reciben los datos en teoria
        val ourPieEntry = ArrayList<PieEntry>()

        ourPieEntry.add(PieEntry(100f, "Asistencias Totales"))
        ourPieEntry.add(PieEntry(250f, "Asistencias Esperadas"))

        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)

        // De aqui para abajo es formato

        val pieShades: ArrayList<Int> = ArrayList()
        pieShades.add(Color.parseColor("#FFBB86FC"))
        pieShades.add(Color.parseColor("#FE810E"))

        ourSet.colors = pieShades
        ourPieChart.data = data

        data.setValueTextColor(Color.DKGRAY)
        data.setValueTextSize(20f)

        ourPieChart.getLegend().setTextColor(Color.DKGRAY)
        ourPieChart.getDescription().setTextColor(Color.DKGRAY)
        ourPieChart.setEntryLabelColor(Color.DKGRAY)

        ourPieChart.description.isEnabled = false
        ourPieChart.setDrawEntryLabels(false)
    }

    fun activityTest(){
        eventCount.setOnClickListener{
            val intent = Intent(this, ListEventsProfits::class.java)
            startActivity(intent)
        }
    }
}