package com.example.appatemporal.framework.viewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.network.dataclasses.DC_Event
import kotlinx.android.synthetic.main.activity_eventandprofitsitem.view.*

class AdapterEventsProfits(private val context: Context) : RecyclerView.Adapter<AdapterEventsProfits.MainViewHolder>() {

    private var dataList = mutableListOf<DC_Event>()

    fun setListData(data:MutableList<DC_Event>){
        dataList = data
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(event:DC_Event){
            itemView.nameEvent.text = event.EventName
            itemView.categoryEvent.text = event.EventCategory
            itemView.ratingEvent.text = event.EventRating.toString()
            itemView.profitsEvent.text = event.EventProfits.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_eventandprofitsitem,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val event:DC_Event = dataList[position]
        holder.bindView(event)
    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0){
            dataList.size
        } else {
            0
        }
    }

}
