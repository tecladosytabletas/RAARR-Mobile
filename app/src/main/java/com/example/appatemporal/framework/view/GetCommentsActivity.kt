package com.example.appatemporal.framework.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.ActivityGetcommentsBinding
import com.example.appatemporal.framework.view.adapters.GetCommentsAdapter
import com.example.appatemporal.framework.view.adapters.GetCommentsViewHolder

class GetCommentsActivity: AppCompatActivity() {
    private lateinit var  binding: ActivityGetcommentsBinding


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityGetcommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()


    }

    fun initRecyclerView(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        binding.RecyclerComment.layoutManager = manager
        binding.RecyclerComment.adapter = GetCommentsAdapter()

        binding.RecyclerComment.addItemDecoration(decoration)

    }

}