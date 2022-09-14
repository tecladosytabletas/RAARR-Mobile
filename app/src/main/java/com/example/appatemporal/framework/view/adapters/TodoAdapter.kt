package com.example.appatemporal.framework.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.databinding.ItemTodoBinding


class TodoAdapter(val list: List<Actividad>) : RecyclerView.Adapter<TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        )
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size




}
class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTodoBinding.bind(itemView)
    fun bind(todoModel: Actividad) {
        binding.txtShowTitle.text = todoModel.nombre
        binding.txtShowArea.text = todoModel.area
        binding.txtShowEstatus.text = todoModel.estatus

    }

}

