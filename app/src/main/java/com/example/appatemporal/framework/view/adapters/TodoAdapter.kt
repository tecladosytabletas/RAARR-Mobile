package com.example.appatemporal.framework.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.databinding.ItemTodoBinding


class TodoAdapter(val list: List<Actividad>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private lateinit var binding : ItemTodoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemId(position: Int): Long {
        return list[position].id_actividad.toLong()
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemTodoBinding.bind(itemView)
        fun bind(todoModel: Actividad) {
            with(itemView) {
                binding.txtShowTitle.text = todoModel.nombre
                binding.txtShowArea.text = todoModel.area
                binding.txtShowEstatus.text = todoModel.estatus

            }
        }
    }

}


