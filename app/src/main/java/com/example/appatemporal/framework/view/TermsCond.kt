package com.example.appatemporal.framework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appatemporal.databinding.ActivityTermsCondBinding

/**
 * Class that inherits from AppCompatActivity
 */
class TermsCond : AppCompatActivity() {

    private lateinit var binding: ActivityTermsCondBinding

    /**
     * Overrides function onCreate and starts the activity
     *
     * @param savedInstanceState: Bundle? -> Saved instance of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsCondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnReturnRegister.setOnClickListener {
            finish()
        }
    }
}