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
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

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
            val profit: String = NumberFormat.getNumberInstance(Locale.US).format(it)
            ourIngresosTotales.text = "$"+profit+" MXN"
        })
    }

    private fun setBCPMbyEvent(tarjeta : Int, efectivo : Int){
        //declare values of the chart
        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(1f, tarjeta.toFloat()))
        barEntries.add(BarEntry(2f, efectivo.toFloat()))
        //bardata set
        val bardataSet = BarDataSet(barEntries,"Metodos de pago")
        bardataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        val data = BarData(bardataSet)
        //pass the data to the BarChar
        ourPMBarChart.data = data
        //declare the XAxis variable
        val xAxis: XAxis = ourPMBarChart.xAxis

        //set the labels on the chart
        val xAxisLabels = listOf("")
        ourPMBarChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)

        //decorative elements of the chart
        ourPMBarChart.axisLeft.setDrawGridLines(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        ourPMBarChart.legend.isEnabled = false
        ourPMBarChart.description.isEnabled = false
        ourPMBarChart.animateY(1000)
        ourPMBarChart.invalidate()
    }

}