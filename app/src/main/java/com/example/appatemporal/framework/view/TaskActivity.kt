package com.example.appatemporal.framework.view


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.LocalDatabase
import com.example.appatemporal.data.localdatabase.entities.Actividad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.appatemporal.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity(), View.OnClickListener {

    private val area_labels = arrayListOf("Personal", "Business", "Insurance", "Shopping", "Banking")
    private val estatus_labels = arrayListOf("Baja", "Media", "Alta")


    val db by lazy {
        LocalDatabase.getInstance(this).actividadDao
    }
    private lateinit var binding : ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameActivity.setOnClickListener(this)
        binding.saveBtn.setOnClickListener(this)


        setUpSpinnerArea()
        setUpSpinnerEstatus()
    }

    private fun setUpSpinnerArea() {
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, area_labels)

        area_labels.sort()

        binding.spinnerArea.adapter = adapter
    }

    private fun setUpSpinnerEstatus() {
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, estatus_labels)

        estatus_labels.sort()

        binding.spinnerEstatus.adapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.saveBtn -> {
                saveTodo()
            }
        }

    }

    private fun saveTodo() {
        val area = binding.spinnerArea.selectedItem.toString()
        val estatus = binding.spinnerEstatus.selectedItem.toString()
        val nombre = binding.nameActivity.text.toString()

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
