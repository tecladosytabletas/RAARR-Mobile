package com.example.appatemporal.framework.view.adapters


import android.app.AlertDialog
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.databinding.ItemTodoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.AddActivity
import com.example.appatemporal.framework.view.DeleteActivity
import com.example.appatemporal.framework.view.ModificarActividad
import com.example.appatemporal.framework.viewModel.DeleteActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ActividadViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // value used to assign the data binding of the xml file
    val binding = ItemTodoBinding.bind(view)

    // Function used to assign a xml element with an attribute of the entity Actividad
    fun render(activityModel: Actividad, viewModel: DeleteActivityViewModel) {
        binding.txtShowTitle.text = activityModel.nombre_actividad
        binding.txtShowArea.text = activityModel.area
        binding.txtShowEstatus.text = activityModel.estatus
        binding.txtShowPrioridad.text = activityModel.prioridad

        // Function that will be initialized once the element ivEditIcon is clicked by the user
        // The function inside it is an intent to the ModificarActividad view
        binding.ivEditIcon.setOnClickListener{
            val intent1 = Intent(itemView.context, ModificarActividad::class.java)
            with(intent1){
                putExtra("id_actividad", activityModel.id_actividad)
                putExtra("nombre_actividad", activityModel.nombre_actividad)
                putExtra("area", activityModel.area)
                putExtra("estatus", activityModel.estatus)
                putExtra("prioridad", activityModel.prioridad)
                putExtra("id_proyecto", activityModel.id_proyecto)
            }
            itemView.context.startActivity(intent1)
        }

        // Function that will be initialized once the element deleteActivityButton
        // is clicked by the user. The function inside it is used to delete an activity
        // And to send the confirmation to delete that activity

        binding.deleteActivityButton.setOnClickListener{
            val repository = Repository(itemView.context)
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("¿Estás seguro?")
            builder.setMessage("¿Estás seguro de que quieres eliminar esta actividad?. Este proceso no puede revertirse")
            // If the user clicks on Eliminar the activity will be erased
            builder.setPositiveButton("Eliminar"){dialogInterface, which ->
                    viewModel.removeActividad(activityModel.id_proyecto,activityModel, repository)
            }
            builder.setNeutralButton("Cancelar"){dialogInterface , which ->

            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            // show the AlertDialog
            alertDialog.show()
        }
    }
}