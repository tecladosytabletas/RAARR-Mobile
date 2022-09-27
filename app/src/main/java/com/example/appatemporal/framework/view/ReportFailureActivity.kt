package com.example.appatemporal.framework.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivityReportFailureBinding


class ReportFailureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportFailureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportFailureBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}