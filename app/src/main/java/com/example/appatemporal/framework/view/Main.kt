package com.example.appatemporal.framework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.LocalDatabase
import com.example.appatemporal.data.localdatabase.dao.ActividadDao
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.localdatabase.entities.Estatus
import kotlinx.coroutines.launch

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actividadDao = LocalDatabase.getInstance(this).actividadDao
        val areaDao = LocalDatabase.getInstance(this).areaDao
        val estatusDao = LocalDatabase.getInstance(this).estatusDao
        val objetivoDao = LocalDatabase.getInstance(this).objetivoDao
        val proyectoDao = LocalDatabase.getInstance(this).proyectoDao

        val estatusList = listOf(
            Estatus(1, "Pendiente", "08/09/2022", "08/09/2022"),
            Estatus(2, "En proceso", "08/09/2022", "08/09/2022"),
            Estatus(3, "Terminado", "08/09/2022", "08/09/2022")
        )

        lifecycleScope.launch{
            estatusDao.insertAll(estatusList)
            estatusDao.getAll().forEach {
                Log.d("Estatus", it.toString())
            }
            estatusDao.deleteAll()

        }

    }

    // Insert activity into database