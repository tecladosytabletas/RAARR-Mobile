package com.example.appatemporal.framework.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.appatemporal.R
import com.example.appatemporal.data.GetTicketStateRequirement
import com.example.appatemporal.databinding.ActivityBoletoEspectadorBinding
import com.example.appatemporal.databinding.ActivityBoletoPorEventoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.ConsultarBoletoViewModel
import com.example.appatemporal.framework.viewModel.GetUserTicketViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class ConsultarBoleto : AppCompatActivity() {
    private lateinit var binding: ActivityBoletoEspectadorBinding
    private val consultarBoletoViewModel: ConsultarBoletoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoletoEspectadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
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
        binding.ratingbar.visibility = android.view.View.INVISIBLE

//        val idUser = getSharedPreferences("user", Context.MODE_PRIVATE).getString("userUid", "").toString()
        val idUser = "pod6xLDUeRNZItm7u93DC5CYbgJ2"
        val idEvento = intent.getStringExtra("idEvento")
        val nombre = intent.getStringExtra("nombre")
        val fecha = intent.getStringExtra("fecha")
        val hora = intent.getStringExtra("hora")
        val lugar = intent.getStringExtra("lugar")
        val direccion = intent.getStringExtra("direccion")
        val ciudad = intent.getStringExtra("ciudad")
        val estado = intent.getStringExtra("estado")
        val hashQr = intent.getStringExtra("hashQr")

        Log.d("id evento seleccionado", idEvento.toString())

        Log.d("hashqrValue Log", hashQr.toString())

        consultarBoletoViewModel.VerifyRate(idUser, idEvento.toString(), repository)
        consultarBoletoViewModel.rateState.observe(this, Observer {
            if (it) {
                Log.d("LogExistence rating", it.toString())
                binding.ratingbar.visibility = android.view.View.INVISIBLE

            } else {
                consultarBoletoViewModel.getStateTicket(hashQr.toString(),idUser,repository)
                consultarBoletoViewModel.ticketState.observe(this, Observer {
                    Log.d("DatAvIEW", it.toString())
                    if (it == false){
                        binding.ratingbar.visibility = android.view.View.VISIBLE
                        binding.ratingbar.setOnRatingBarChangeListener { ratingBar, fl, b ->
                            Log.d("rating bar Log", fl.toString())
                            consultarBoletoViewModel.addRating(idUser, idEvento.toString(), fl, repository)
                            Toast.makeText(this, "El evento ha sido calificado", Toast.LENGTH_SHORT).show()
                            Handler(Looper.myLooper()!!).postDelayed(Runnable{
                                binding.ratingbar.visibility = android.view.View.INVISIBLE
                            },1000)
                        }

                    }
                })
            }
        })






        binding.NombreEventoBoleto.text = nombre
        binding.FechaEvento.text = fecha
        binding.LugarEvento.text = lugar
        binding.DireccionEvento.text = direccion
        binding.EstadoEvento.text = ciudad + ", " + estado

        val CODIGO_QR : BarcodeEncoder = BarcodeEncoder()
        val BITMAP: Bitmap =  CODIGO_QR.encodeBitmap(hashQr, BarcodeFormat.QR_CODE, 100, 100)
        var TARJETA_CODIGO_QR: ImageView = findViewById(R.id.CodigoQr)
        TARJETA_CODIGO_QR.setImageBitmap(BITMAP)

    }
    //Esto es un comentario para que pueda commitear
}