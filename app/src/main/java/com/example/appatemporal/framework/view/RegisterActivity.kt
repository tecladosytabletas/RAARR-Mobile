package com.example.appatemporal.framework.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        }
}