package com.example.appatemporal.framework.view

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.appatemporal.R
import com.example.appatemporal.databinding.BoletoEspectadorActivityBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.GetUserTicketViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class ConsultarBoletoActivity : AppCompatActivity() {
    private val getTicketViewModel : GetUserTicketViewModel by viewModels()
    //private lateinit var binding: BoletoEspectadorActivityBinding
    //private val repository = Repository(this)
    private var hash_qr : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boleto_espectador_activity)

       //getTicketViewModel.getUserTicket("x02BQ0RcJmRjhsvYJJ9z", "12hEWP8xQQgQGjCyuWon","UUX75bE59gTT0RBOqHLB", repository)
        //getTicketViewModel.ticket.observe(this, Observer { value ->
        //    hash_qr = value.toString()
        //})

        val CODIGO_QR : BarcodeEncoder = BarcodeEncoder()
        val BITMAP: Bitmap =  CODIGO_QR.encodeBitmap(hash_qr, BarcodeFormat.QR_CODE, 100, 100)
        var TARJETA_CODIGO_QR: ImageView = findViewById(R.id.CodigoQr)
        TARJETA_CODIGO_QR.setImageBitmap(BITMAP)

    }
}