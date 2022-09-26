package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.appatemporal.R
import com.example.appatemporal.databinding.DetailedMetricsBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.GetEventNameViewModel
import com.example.appatemporal.framework.viewModel.GetEventProfitViewModel
import com.example.appatemporal.framework.viewModel.GetPMbyTicketsViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class DetailedMetrics : AppCompatActivity(){

    private lateinit var binding : DetailedMetricsBinding
    private lateinit var ourDashTitle : TextView
    private lateinit var ourPMBarChart: BarChart
    private lateinit var ourIngresosTotales : TextView

    private val eventNameViewModel : GetEventNameViewModel by viewModels()
    private val totalProfitsViewModel : GetEventProfitViewModel by viewModels()
    private val totalTicketsbyPM : GetPMbyTicketsViewModel by viewModels()

    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_metrics)

        binding = DetailedMetricsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Implementación de la NavBar
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

        ourDashTitle = findViewById(R.id.dashTitle)
        ourIngresosTotales = findViewById(R.id.profitsEvent)
        ourPMBarChart = findViewById(R.id.PMLinechart)

        repository = Repository(this)

        val tempEventId : String = "DM"

        setEventName(tempEventId)
        setTotalProfit(tempEventId)

        var ventasTarjeta : Int = 0
        var ventasEfectivo : Int = 0
        totalTicketsbyPM.getPMbyTickets(tempEventId,repository)
        totalTicketsbyPM.countPM.observe(this, Observer{
            ventasTarjeta = it.first
            ventasEfectivo = it.second
            setBCPMbyEvent(ventasTarjeta, ventasEfectivo)
        })

    }

    private fun setEventName(eid:String) {
        repository = Repository(this)
        eventNameViewModel.getEventName(eid,repository)
        eventNameViewModel.eventName.observe(this, Observer{
            ourDashTitle.text = "${it}"
        })
    }

    private fun setTotalProfit(eid:String) {
        repository = Repository(this)
        totalProfitsViewModel.getEventProfit(eid,repository)
        totalProfitsViewModel.eventProfit.observe(this, Observer{
            ourIngresosTotales.text = "${it}"
        })
    }

    private fun setBCPMbyEvent(tarjeta : Int, efectivo : Int){
        val tarjetf = tarjeta.toFloat()
        val efectivof = efectivo.toFloat()
        //declare x values
        val xvalues = ArrayList<String>()
        xvalues.add("Tarjeta")
        xvalues.add("Efectivo")
        //declare y values
        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(tarjeta.toFloat(), 0))
        barEntries.add(BarEntry(efectivo.toFloat(), 0))
        //bardata set
        val bardataset = BarDataSet(barEntries,"Metodos de pago")
        bardataset.color = resources.getColor(R.color.blue)
        //make a bar data
        val data = BarData(xvalues,bardataset)
        //pass the data to the BarChar
        ourPMBarChart.data = data
        //Decoration and animation of the BarChar
        ourPMBarChart.setBackgroundColor(resources.getColor(R.color.atemporal_purple))
        ourPMBarChart.animateXY(2000,2000)
    }

}