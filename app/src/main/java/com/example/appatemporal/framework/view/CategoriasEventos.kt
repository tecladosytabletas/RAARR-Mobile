package com.example.appatemporal.framework.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.CategoriasEventosBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.CategoryModel
import com.example.appatemporal.framework.view.adapters.CategoriasEventosAdapter
import com.example.appatemporal.framework.view.adapters.ProjectsAdapter
import com.example.appatemporal.framework.viewModel.CategoriasEventosViewModel

class CategoriasEventos: AppCompatActivity() {
    private lateinit var binding: CategoriasEventosBinding
    private val viewModel : CategoriasEventosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoriasEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        viewModel.getCategories(repository)
        viewModel.categories.observe(this, Observer{ categoryList ->
            binding.RecyclerView.layoutManager = LinearLayoutManager(this)
            binding.RecyclerView.adapter = CategoriasEventosAdapter(categoryList)

        })


    }


}
