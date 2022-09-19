package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.AddCostosBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.CostoAdapter
import com.example.appatemporal.framework.viewModel.DeleteCostoViewModel
import kotlinx.coroutines.launch

class DeleteCosto : AppCompatActivity(){
    private val  viewModel : DeleteCostoViewModel by viewModels()
    private lateinit var binding: AddCostosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddCostosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        lifecycleScope .launch {
            initRecyclerView(repository)
        }

        binding.newCostoButton.setOnClickListener {
            val intent = Intent(this, AddNewCostoForm::class.java)
            startActivity(intent)
        }
    }

    private suspend fun initRecyclerView(repository: Repository) {
        val costoList = viewModel.getCosto(repository)
        binding.costoRv.layoutManager = LinearLayoutManager(this)
        binding.costoRv.adapter = CostoAdapter(costoList)
        // Log each project
        costoList.forEach {
            Log.d("Project", it.toString())
        }
    }
}