package com.example.appatemporal.framework.view

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.AddActivitiesBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ProjectsAdapter
import com.example.appatemporal.framework.view.adapters.TodoAdapter
import com.example.appatemporal.framework.viewModel.AddActivityViewModel
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding : AddActivitiesBinding
    private val viewModel: AddActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        lifecycleScope .launch {
            initRecyclerView(repository)
        }

        binding.newTaskButton.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }

    }

    private suspend fun initRecyclerView(repository: Repository) {
        val activityList = viewModel.getActivities(1, repository)
        binding.todoRv.layoutManager = LinearLayoutManager(this)
        binding.todoRv.adapter = TodoAdapter(activityList)
        // Log each project
        activityList.forEach {
            Log.d("Activity", it.toString())
        }
    }
}


