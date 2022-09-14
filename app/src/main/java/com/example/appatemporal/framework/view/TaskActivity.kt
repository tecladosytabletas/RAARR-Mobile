package com.example.appatemporal.framework.view


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.LocalDatabase
import com.example.appatemporal.data.localdatabase.entities.Actividad
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.appatemporal.data.localdatabase.dao.ActividadDao
import java.util.*

class TaskActivity : AppCompatActivity(), View.OnClickListener {

    private val labels = arrayListOf("Personal", "Business", "Insurance", "Shopping", "Banking")


    val db by lazy {
        LocalDatabase.getInstance(this).actividadDao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        nameActivity.setOnClickListener(this)
        saveBtn.setOnClickListener(this)


        setUpSpinnerArea()
        setUpSpinnerEstatus()
    }

    private fun setUpSpinnerArea() {
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, labels)

        labels.sort()

        spinnerArea.adapter = adapter
    }

    private fun setUpSpinnerEstatus() {
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, labels)

        labels.sort()

        spinnerEstatus.adapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.saveBtn -> {
                saveTodo()
            }
        }

    }

    private fun saveTodo() {
        val area = spinnerArea.selectedItem.toString()
        val estatus = spinnerEstatus.selectedItem.toString()
        val nombre = nameActivity.text.toString()

        GlobalScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                return@withContext db.insertTask(
                    Actividad(
                        nombre,
                        area,
                        estatus,
                        "a",
                        "a",

                    )
                )
            }
            finish()
        }

    }


}
