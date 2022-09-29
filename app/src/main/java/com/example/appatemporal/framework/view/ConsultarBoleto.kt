package com.example.appatemporal.framework.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityBoletoEspectadorBinding
import com.example.appatemporal.databinding.ActivityBoletoPorEventoBinding
import com.example.appatemporal.framework.viewModel.GetUserTicketViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class ConsultarBoleto : AppCompatActivity() {
    private lateinit var binding: ActivityBoletoEspectadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoletoEspectadorBinding.inflate(layoutInflater)
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

        val idEvento = intent.getStringExtra("idEvento")
        val nombre = intent.getStringExtra("nombre")
        val fecha = intent.getStringExtra("fecha")
        val hora = intent.getStringExtra("hora")
        val lugar = intent.getStringExtra("lugar")
        val direccion = intent.getStringExtra("direccion")
        val ciudad = intent.getStringExtra("ciudad")
        val estado = intent.getStringExtra("estado")
        val hashQr = intent.getStringExtra("hashQr")

        binding.ivShareEvent.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Checa este nuevo evento!! https://www.youtube.com/" + idEvento)
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "send to "))
        }

        binding.navbar.metricsIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        Log.d("id evento seleccionado", idEvento.toString())

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
}