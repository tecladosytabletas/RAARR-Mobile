package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.entities.Costo
import com.example.appatemporal.databinding.CostoTaskBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.AddNewCostoViewModel
import kotlinx.coroutines.launch

class AddNewCostoForm : AppCompatActivity(){
    private val viewModel: AddNewCostoViewModel by viewModels()
    private lateinit var binding : CostoTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CostoTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)

        // Set click listener
        binding.savecostoBtn.setOnClickListener {
            // Get values from view
            val name = binding.nameCosto.text.toString()
            val amount = binding.montoCosto.text.toString().toInt()

            val costo: Costo = Costo(0, name, amount)

            lifecycleScope.launch{
                viewModel.addNewCosto(costo, repository)
            }


            // Go back to main activity
            val intent = Intent(this, DeleteCosto::class.java)
            startActivity(intent)

        }

    }
}