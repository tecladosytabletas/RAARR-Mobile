package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.databinding.ActivityTaskBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.AddNewActivityViewModel
import kotlinx.coroutines.launch

class AddNewActivityForm : AppCompatActivity(){
    private val viewModel: AddNewActivityViewModel by viewModels()
    private lateinit var binding : ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)

        // Set click listener
        binding.saveBtn.setOnClickListener {
            // Get values from view
            val name = binding.nameActivity.text.toString()

            val actividad: Actividad = Actividad(0, name)

            lifecycleScope.launch{
                viewModel.addNewActividad(actividad, repository)
            }


            // Go back to main activity
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)

        }

    }
}