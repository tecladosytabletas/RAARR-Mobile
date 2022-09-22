package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivitySoporteBinding

class SupportActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySoporteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoporteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContacto3.setOnClickListener {
            val intent = Intent(this, ContactInfo::class.java)
            startActivity(intent)
        }

        binding.btnTerminos3.setOnClickListener {
            val intent = Intent(this, TermsCond::class.java)
            startActivity(intent)
        }
    }
}