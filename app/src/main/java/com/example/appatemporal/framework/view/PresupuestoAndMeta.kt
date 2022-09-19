package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.example.appatemporal.databinding.ObjectivePresupuestoMetaBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ModifyPresupuesto
import com.example.appatemporal.framework.viewModel.PresupuestoOrganizadorViewModel
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PresupuestoAndMeta: AppCompatActivity()  {

    private val viewModel: PresupuestoOrganizadorViewModel by viewModels()
    private lateinit var binding : ObjectivePresupuestoMetaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ObjectivePresupuestoMetaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        var myExtras :Bundle? = intent.extras
        var idProyecto: Int=  myExtras?.getInt("id_proyecto")?:-1

        lifecycleScope.launch{
            var myProyecto : Proyecto= viewModel.getProyectoByid(idProyecto,repository)
            binding.tvNewPresupuesto.text="Presupuesto: "+ myProyecto.presupuesto.toString()
            var presupuesto: String=myProyecto.presupuesto.toString()
            binding.tvNewPresupuesto.setOnClickListener {
                ModifyPresupuesto().show(supportFragmentManager.beginTransaction(), "newTaskTag")
            }
        }


            binding.tvDeletePresupuesto.setOnClickListener{
                val intent = Intent(binding.tvDeletePresupuesto.context, PresupuestoAndMeta::class.java)
                with(intent){
                    putExtra("id_proyecto", idProyecto)
                }
                val repository = Repository(binding.tvDeletePresupuesto.context)
                val builder = AlertDialog.Builder(binding.tvDeletePresupuesto.context)
                builder.setTitle("¿Estás seguro?")
                builder.setMessage("¿Estás seguro de que quieres Establecer el presupuesto como 0?")
                builder.setPositiveButton("Eliminar"){dialogInterface, which ->
                    CoroutineScope(Dispatchers.IO ).launch {
                        viewModel.updatePrespuesto(0.0,idProyecto, repository)
                    }
                    binding.tvDeletePresupuesto.context.startActivity(intent)
                }
                builder.setNeutralButton("Cancelar"){dialogInterface , which ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }

    }
}