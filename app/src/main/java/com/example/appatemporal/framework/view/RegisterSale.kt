package com.example.appatemporal.framework.view

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.appatemporal.databinding.ActivityRegisterSaleBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.RegisterSaleViewModel

class RegisterSale : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterSaleBinding
    private val registerSaleViewModel: RegisterSaleViewModel by viewModels()
    private var ticketType = arrayListOf<String>()
    private lateinit var idEvent: String
    private lateinit var idFuncion: String
    private val activityContext = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mSpinner2 = binding.spinner2
        val mSpinner3 = binding.spinner3

        idEvent = "ih83lF54LmwoNdFuopeB"
        idFuncion = "qIdvPoINxUTuvPWt8UiB"

        registerSaleViewModel.getDropdownNames("ih83lF54LmwoNdFuopeB", Repository(this))
        registerSaleViewModel.dropdownList.observe(this, Observer {
            Log.d("dropdown list log", it.toString())
            val ticketTypeString = arrayListOf<String>()
            for (name in it) {
                ticketTypeString.add("${name.first}: $${name.second}")
                ticketType.add("${name.third}")
            }
            val mArrayAdapter2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ticketTypeString)
            mArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item)
            mSpinner2.adapter = mArrayAdapter2
        })

        val payment = arrayListOf<String>("Efectivo", "Tarjeta")

        val mArrayAdapter3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, payment)
        mArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_item)

        mSpinner3.adapter = mArrayAdapter3

        binding.spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                registerSaleViewModel.getTicketAvailability(ticketType[p2], idEvent, idFuncion, Repository(activityContext))
                registerSaleViewModel.ticketAvailability.observe(activityContext, Observer {
                    //Log.d("SelectedDropdown", it.toString())
                    if (it.first < it.second) {
                        binding.btnRegisterSale.isEnabled = true
                        // Hacer Lógica de boton set on click listener para registrar venta
                        // Volver a llamar a la función de getTicketAvailability con parámetro de tipo boleto p2

                    } else {
                        binding.btnRegisterSale.isEnabled = false
                    }
                })
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}