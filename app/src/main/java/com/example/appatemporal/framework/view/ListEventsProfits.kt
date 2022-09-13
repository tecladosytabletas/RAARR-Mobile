package com.example.appatemporal.framework.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.R
import com.example.appatemporal.framework.viewModel.AdapterEventsProfits
import com.example.appatemporal.framework.viewModel.VMEventProfits
import kotlinx.android.synthetic.main.activity_rveventsandprofits.*

class ListEventsProfits: AppCompatActivity() {
    private lateinit var adapter: AdapterEventsProfits
    private val viewModel by lazy { ViewModelProviders.of(this).get(VMEventProfits::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rveventsandprofits)

        adapter = AdapterEventsProfits(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

        observeData()
    }

    fun observeData(){
        shimmer_view_container.startShimmer()
        viewModel.fetchEventData().observe(this, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}

