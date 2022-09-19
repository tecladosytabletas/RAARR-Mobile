package com.example.appatemporal.framework.view.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import com.example.appatemporal.databinding.FormularioPresupuestoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.DeleteActivity
import com.example.appatemporal.framework.view.PresupuestoAndMeta
import com.example.appatemporal.framework.viewModel.PresupuestoOrganizadorViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModifyPresupuesto :BottomSheetDialogFragment() {
    private lateinit var binding: FormularioPresupuestoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository(requireContext())

        var myExtras :Bundle? = intent.extras
        binding.descPresupuesto.setText(myExtras?.getString("Presupuesto"))
        val idproject: Int = myExtras?.getInt("id_proyecto")?:-1

        binding.saveButton.setOnClickListener {
            val taskViewModel = PresupuestoOrganizadorViewModel()
            val presupuesto = binding.descPresupuesto.text.toString().toDouble()
            CoroutineScope(Dispatchers.IO ).launch {
                taskViewModel.updatePrespuesto(presupuesto,idproject, repository)
            }
            val intent = Intent(requireContext(), PresupuestoAndMeta::class.java)
            with(intent){
                putExtra("id_proyecto", idproject)
            }
            startActivity(intent)
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FormularioPresupuestoBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction() {
        binding.descPresupuesto.setText("")
        dismiss()
    }
}
