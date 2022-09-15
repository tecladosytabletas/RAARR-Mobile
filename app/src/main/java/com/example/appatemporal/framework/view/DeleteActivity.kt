package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.AddActivitiesBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ActividadAdapter
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
        lifecycleScope .launch {
            initRecyclerView(repository)
        }

        binding.newTaskButton.setOnClickListener {
            val intent = Intent(this, AddNewActivityForm::class.java)
            startActivity(intent)
        }
    }

    private suspend fun initRecyclerView(repository: Repository) {
        val activityList = viewModel.getActivities(repository)
        binding.todoRv.layoutManager = LinearLayoutManager(this)
        binding.todoRv.adapter = ActividadAdapter(activityList)
        // Log each project
        activityList.forEach {
            Log.d("Project", it.toString())
        }
    }
}