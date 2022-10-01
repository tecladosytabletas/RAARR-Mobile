package com.example.appatemporal.framework.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivityGetcommentsBinding

class GetCommentsActivity: AppCompatActivity() {
    private lateinit var  binding: ActivityGetcommentsBinding


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityGetcommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}