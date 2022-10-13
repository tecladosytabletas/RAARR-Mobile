package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivitySoporteBinding
import com.google.firebase.auth.FirebaseAuth

class SupportActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySoporteBinding
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoporteBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.btnFAQ3.setOnClickListener{
            val userRol = getSharedPreferences("user", Context.MODE_PRIVATE)
                .getString("rol", "").toString()
            if(userRol == "Espectador"){
                val intent = Intent(this,FaqEspectador::class.java)
                startActivity(intent)
            } else if(userRol == "Organizador") {
                val intent = Intent(this, FaqOrganizador::class.java)
                startActivity(intent)
            }else if(userRol =="Ayudante"){
                val intent = Intent(this, FaqAyudante::class.java)
                startActivity(intent)
            }
        }

        binding.btnContacto3.setOnClickListener {
            val intent = Intent(this, ContactInfo::class.java)
            startActivity(intent)
        }

        binding.btnTerminos3.setOnClickListener {
            val intent = Intent(this, TermsCond::class.java)
            startActivity(intent)
        }

        binding.btnFallas3.setOnClickListener {
            val intent = Intent(this, ReportFailureActivity::class.java)
            startActivity(intent)
        }
    }
}