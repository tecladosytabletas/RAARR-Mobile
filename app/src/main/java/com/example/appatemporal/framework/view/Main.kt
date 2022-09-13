package com.example.appatemporal.framework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.appatemporal.R


class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userUid = intent.getStringExtra("userUid").toString()

        Log.d("User Auth Successfully", userUid)
    }
}

    // Insert activity into database