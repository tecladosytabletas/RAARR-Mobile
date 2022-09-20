package com.example.appatemporal.framework.view.consultarboletoespectador

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.appatemporal.R
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class Consultar_Boleto_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boleto_espectador_activity)
        val CODIGO_QR :BarcodeEncoder = BarcodeEncoder()
        val BITMAP: Bitmap =  CODIGO_QR.encodeBitmap("Hola", BarcodeFormat.QR_CODE, 100, 100)
        var TARJETA_CODIGO_QR: ImageView = findViewById(R.id.CodigoQr)
        TARJETA_CODIGO_QR.setImageBitmap(BITMAP)
    }
}