package com.example.appatemporal.framework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.appatemporal.databinding.ActivityRegisterQrviewBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.MainViewModel
import com.example.appatemporal.framework.viewModel.ScanQRViewModel
import com.google.zxing.integration.android.IntentIntegrator

class RegisterQRView : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterQrviewBinding

    private lateinit var scanQRViewModel : ScanQRViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterQrviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val btnQR = binding.btnConsulta
        btnQR.setOnClickListener{
            initScanner()
        }
    }
    private fun initScanner(){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanea el boleto")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val repository = Repository(this)


        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "No se encontró un valor.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Boleto escaneado con éxito.", Toast.LENGTH_SHORT).show()
                scanQRViewModel.updateTicketValue(result.contents, repository)
                scanQRViewModel.qrcode.observe(this, Observer {
                    if(it == true){
                        Toast.makeText(this,"Boleto valido!",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Boleto ya fue Escaneado.",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}