package com.example.appatemporal.framework.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.appatemporal.databinding.ActivityMainBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.MainViewModel


class Main : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val repository = Repository(this)

        val mainViewModel : MainViewModel by viewModels()

        val userUid = getSharedPreferences("userUid", Context.MODE_PRIVATE)
                        .getString("userUid", "").toString()

        Log.d("User Auth Successfully", userUid)

        mainViewModel.getUserLocalDB(userUid, repository)

        mainViewModel.userData.observe(this, Observer {
            binding.textView2.text = "${it.nombre} ${it.apellido}"
            binding.textView3.text = it.rol
        })

    }
}