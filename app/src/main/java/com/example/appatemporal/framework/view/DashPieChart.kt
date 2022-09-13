package com.example.appatemporal.framework.view

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.R
import com.example.appatemporal.data.network.dataclasses.DashPieModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class DashPieChart : AppCompatActivity(){
    private lateinit var ourPieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        ourPieChart = findViewById(R.id.dashPieChart)
        populatePieChart()
    }
    // Ejemplo de poblar la grafica

    private fun populatePieChart() {
        //an array to store the pie slices entry
        val ourPieEntry = ArrayList<PieEntry>()

        ourPieEntry.add(PieEntry(100f, "Asistencias Totales"))
        ourPieEntry.add(PieEntry(250f, "Asistencias Esperadas"))

        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)

        val pieShades: ArrayList<Int> = ArrayList()
        pieShades.add(Color.parseColor("#008000"))
        pieShades.add(Color.parseColor("#FF0000"))

        ourSet.colors = pieShades
        ourPieChart.data = data

        data.setValueTextColor(Color.LTGRAY)
        data.setValueTextSize(20f)

        ourPieChart.getLegend().setTextColor(Color.DKGRAY)

        ourPieChart.getDescription().setTextColor(Color.DKGRAY)

        ourPieChart.setEntryLabelColor(Color.DKGRAY)

    }
}