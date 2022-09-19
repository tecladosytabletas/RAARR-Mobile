package com.example.appatemporal.framework.view

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.R
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
        var ganancia:Double = (myExtras?.getDouble("gananciaK")?:-1) as Double
        var presupuesto:Double = (myExtras?.getDouble("presupuestoK")?:-1) as Double
        var meta:Double = (myExtras?.getDouble("metaK")?:-1) as Double

        lifecycleScope.launch{
            var myProyecto : Proyecto= viewModel.getProyectoByid(idProyecto,repository)
            binding.tvNewPresupuesto.text="Presupuesto: "+ myProyecto.presupuesto.toString()
            var myMeta : Proyecto= viewModel.getProyectoByid(idProyecto,repository)
            binding.tvMetaName.text="Meta: "+ myMeta.meta.toString()
            binding.MetaValue.text = myMeta.meta.toString()
        }

        // On button click, a bundle is initialized and the
        // text from the EditText is passed in the custom
        // fragment using this bundle
        binding.tvNewPresupuesto.setOnClickListener {
            val fragment = ModifyPresupuesto()
            val bundle = Bundle()
            bundle.putString("idProyecto", idProyecto.toString())
            bundle.putString("ganancia", ganancia.toString())
            bundle.putString("presupuesto", presupuesto.toString())
            bundle.putString("meta", meta.toString())
            fragment.arguments = bundle
            fragment.show(supportFragmentManager, "newTaskTag")

        }
        binding.ivEditMeta.setOnClickListener{
            val fragment = ModifyMeta()
            val bundle = Bundle()
            bundle.putString("idProyecto", idProyecto.toString())
            bundle.putString("ganancia", ganancia.toString())
            bundle.putString("presupuesto", presupuesto.toString())
            bundle.putString("meta", meta.toString())
            fragment.arguments = bundle
            fragment.show(supportFragmentManager, "newTaskTag")
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

            binding.deleteMetaButton.setOnClickListener{
                val intent = Intent(binding.deleteMetaButton.context, PresupuestoAndMeta::class.java)
                with(intent){
                    putExtra("id_proyecto", idProyecto)
                }
                val repository = Repository(binding.deleteMetaButton.context)
                val builder = AlertDialog.Builder(binding.deleteMetaButton.context)
                builder.setTitle("¿Estás seguro?")
                builder.setMessage("¿Estás seguro de que quieres Establecer la meta como 0?")
                builder.setPositiveButton("Eliminar"){dialogInterface, which ->
                    CoroutineScope(Dispatchers.IO ).launch {
                        viewModel.updateMeta(0.0,idProyecto, repository)
                    }
                    binding.deleteMetaButton.context.startActivity(intent)
                }
                builder.setNeutralButton("Cancelar"){dialogInterface , which ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }

    }
}