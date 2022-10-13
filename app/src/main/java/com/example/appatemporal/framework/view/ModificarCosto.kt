package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.databinding.ModifyNewCostoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.DeleteCostoViewModel
import com.example.appatemporal.data.localdatabase.entities.Costo
import com.example.appatemporal.data.localdatabase.entities.Proyecto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
/**
 * This file is linked with modify_new_costo.xml
 * This file is in charge of controlling the logic behind the funtion modify a costo
 *
 * */
class ModificarCosto : AppCompatActivity() {
    private val viewModel: DeleteCostoViewModel by viewModels()
    private lateinit var binding: ModifyNewCostoBinding
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyNewCostoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Define the context and keep it on a variable
        val repository = Repository(this)

        //Extract parameters that came on the intent that is used to access this interface
        var myExtras: Bundle? = intent.extras
        binding.newNameCosto.setText(myExtras?.getString("nombre_actividad"))
        binding.newMontoCosto.setText(myExtras?.getInt("monto").toString())
        val idProyecto: Int=  myExtras?.getInt("id_proyecto")?:-1
        val idcosto: Int = myExtras?.getInt("id_costo") ?: -1
        // Set click listener
        binding.newcostoBtn.setOnClickListener {
            val name = binding.newNameCosto.text.toString()
            val amount = binding.newMontoCosto.text.toString()
            /**
             * In this function it involves the recognition of the null and blank spaces in order
             * to prevent the insertion or modification of negative values
             */
            if (name.isEmpty() && amount.isEmpty()) {
                Toast.makeText(this, "No se especificó ningún dato", Toast.LENGTH_SHORT).show()
            }
            else if (name.isEmpty()) {
                Toast.makeText(this, "No se especificó nombre", Toast.LENGTH_SHORT).show()
            }
            else if (amount.isEmpty()) {
                Toast.makeText(this, "No se especificó el costo", Toast.LENGTH_SHORT).show()
            }
            else {
                val amount = binding.newMontoCosto.text.toString().toInt()
                val costo: Costo = Costo(idcosto, name, amount, idProyecto)
                lifecycleScope.launch {
                    viewModel.updateCosto(costo, repository)
                }
                val intent = Intent(this, DeleteCosto::class.java)
                with(intent){
                    putExtra("id_proyecto", idProyecto)
                }
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