package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.AddActivitiesBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ActividadAdapter
import com.example.appatemporal.framework.view.adapters.ProjectsAdapter
import com.example.appatemporal.framework.viewModel.DeleteActivityViewModel
import kotlinx.coroutines.launch

class DeleteActivity : AppCompatActivity(){
    private val  viewModel : DeleteActivityViewModel by viewModels()
    private lateinit var binding: AddActivitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        var myExtras :Bundle? = intent.extras
        var idProyecto: Int=  myExtras?.getInt("id_proyecto")?:-1
        initRecyclerView(repository, idProyecto)
        binding.newTaskButton.setOnClickListener {
            val intent = Intent(this, AddNewActivityForm::class.java)
            with(intent){
                putExtra("id_proyecto", idProyecto)
            }
            startActivity(intent)
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
    }

    private fun initRecyclerView( repository: Repository,id: Int) {
        viewModel.getAllActivitiesid(id,repository)
        viewModel.activities.observe(this, Observer { activityList ->
            binding.todoRv.layoutManager = LinearLayoutManager(this)
            binding.todoRv.adapter = ActividadAdapter(activityList, viewModel)
        })
    }
}