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
        var myExtras :Bundle? = intent.extras

        binding.navbar.homeIcon.setOnClickListener {
            finish()
        }

        binding.navbar.budgetIcon.setOnClickListener {
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

        binding.navbar.ticketsIcon.setOnClickListener {
            finish()
        }

        binding.navbar.metricsIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        var idProyecto: Int=  myExtras?.getInt("id_proyecto")?:-1
        lifecycleScope .launch {
            initRecyclerView(repository, idProyecto)
        }
        binding.navbar.homeIcon.setOnClickListener {
            finish()
        }

        binding.navbar.budgetIcon.setOnClickListener {
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

        binding.navbar.ticketsIcon.setOnClickListener {
            finish()
        }

        binding.navbar.metricsIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        binding.newCostoButton.setOnClickListener {
            val intent = Intent(this, AddNewCostoForm::class.java)
            with(intent){
                putExtra("id_proyecto", idProyecto)
            }

            startActivity(intent)
        }
    }

    private suspend fun initRecyclerView(repository: Repository, idProyecto: Int) {
        val costoList = viewModel.getCosto(repository, idProyecto)
        binding.costoRv.layoutManager = LinearLayoutManager(this)
        binding.costoRv.adapter = CostoAdapter(costoList)
        // Log each project
        costoList.forEach {
            Log.d("Project", it.toString())
        }
    }
}