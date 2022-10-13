package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.R
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.DeleteActivityViewModel
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.databinding.ModifyNewActivityBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ModificarActividad : AppCompatActivity(){
    private val viewModel: DeleteActivityViewModel by viewModels()
    private var auth = FirebaseAuth.getInstance()
    private lateinit var binding : ModifyNewActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyNewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        var myExtras :Bundle? = intent.extras
        binding.nameModifedActivity.setText(myExtras?.getString("nombre_actividad"))
        val idactividad: Int = myExtras?.getInt("id_actividad")?:-1
        val idproyecto: Int = myExtras?.getInt("id_proyecto")?:-1

        val areaList = resources.getStringArray(R.array.areaList)
        val arrayAdapter1 = ArrayAdapter(this, R.layout.dropdown_menu, areaList)
        val autocompleteTV1 = findViewById<AutoCompleteTextView>(R.id.spinnerModifiedArea)
        autocompleteTV1.setAdapter(arrayAdapter1)

        val estatusList = resources.getStringArray(R.array.estatusList)
        val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_menu, estatusList)
        val autocompleteTV2 = findViewById<AutoCompleteTextView>(R.id.spinnerModifiedEstatus)
        autocompleteTV2.setAdapter(arrayAdapter2)

        val prioridadList = resources.getStringArray(R.array.prioridadList)
        val arrayAdapter3 = ArrayAdapter(this, R.layout.dropdown_menu, prioridadList)
        val autocompleteTV3 = findViewById<AutoCompleteTextView>(R.id.spinnerModifiedPrioridad)
        autocompleteTV3.setAdapter(arrayAdapter3)

        // Set click listener
        binding.saveModifiedBtn.setOnClickListener {
            Log.d("idPrueba", idproyecto.toString())
            Log.d("idActividad", idactividad.toString())

            // Get values from view
            val name = binding.nameModifedActivity.text.toString()
            val area = binding.spinnerModifiedArea.text.toString()
            val estatus = binding.spinnerModifiedEstatus.text.toString()
            val prioridad = binding.spinnerModifiedPrioridad.text.toString()
            if (name.isBlank() || area.isBlank() || estatus.isBlank() || prioridad.isBlank()){
                Toast.makeText(this, "Faltan campos por completar", Toast.LENGTH_SHORT).show()
            }
            else {
                val actividad: Actividad = Actividad( idactividad, 0,name, area, estatus, prioridad, idproyecto)
                viewModel.updateActividad(name,estatus,area,prioridad, idactividad, repository)
                // Go back to main activity
                val intent = Intent(this, DeleteActivity::class.java)
                intent.putExtra("id_proyecto", idproyecto)
                startActivity(intent)
            }
        }

        val userRole = getSharedPreferences("user", Context.MODE_PRIVATE).getString("rol", "").toString()
        // ----------------------------Header------------------------------------

        // Visibility

        if(auth.currentUser == null){
            binding.header.buttonsHeader.visibility = android.view.View.GONE
        }

        // Intents
        binding.header.logoutIcon.setOnClickListener{
            Log.d("Sesion", "Salió")
            auth.signOut()
            val userSharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)
            var sharedPrefEdit = userSharedPref.edit()
            sharedPrefEdit.remove("userUid")
            sharedPrefEdit.clear().apply()
            val intent = Intent(this, CheckIfLogged::class.java)
            startActivity(intent)
        }

        binding.header.supportIcon.setOnClickListener{
            val intent = Intent(this, SupportActivity::class.java)
            startActivity(intent)
        }
        // ----------------------------Navbar------------------------------------


        Log.d("Rol", userRole)

        // Visibility
        if ((userRole == "Espectador" && auth.currentUser != null) || userRole == "") {
            binding.navbar.budgetIcon.visibility = android.view.View.GONE
            binding.navbar.metricsIcon.visibility = android.view.View.GONE
            binding.navbar.budgetText.visibility = android.view.View.GONE
            binding.navbar.metricsText.visibility = android.view.View.GONE
        } else if (userRole == "Ayudante" && auth.currentUser != null) {
            binding.navbar.budgetIcon.visibility = android.view.View.GONE
            binding.navbar.metricsIcon.visibility = android.view.View.GONE
            binding.navbar.budgetText.visibility = android.view.View.GONE
            binding.navbar.metricsText.visibility = android.view.View.GONE
            binding.navbar.eventsIcon.visibility = android.view.View.GONE
            binding.navbar.eventsText.visibility = android.view.View.GONE
            binding.navbar.homeIcon.visibility = android.view.View.GONE
            binding.navbar.homeText.visibility = android.view.View.GONE
        }

        // Intents
        binding.navbar.homeIcon.setOnClickListener {
            if(userRole == "Organizador" && auth.currentUser != null){
                val intent = Intent(this, ActivityMainHomepageOrganizador::class.java)
                startActivity(intent)
            }else {
                val intent = Intent(this, ActivityMainHomepageEspectador::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.eventsIcon.setOnClickListener {
            if(userRole == "Organizador" && auth.currentUser != null) {
                val intent = Intent(this,ActivityMisEventosOrganizador::class.java)
                startActivity(intent)
            } else if (userRole == "Espectador" && auth.currentUser != null) {
                val intent = Intent(this,CategoriasEventos::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, CheckIfLogged::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.budgetIcon.setOnClickListener {
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

        binding.navbar.ticketsIcon.setOnClickListener {
            if ((userRole == "Espectador" || userRole == "Organizador") && auth.currentUser != null) {
                val intent = Intent(this, BoletoPorEventoActivity::class.java)
                startActivity(intent)
            } else if (userRole == "Ayudante" && auth.currentUser != null) {
                val intent = Intent(this, RegisterQRView::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, CheckIfLogged::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.metricsIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

    }
}