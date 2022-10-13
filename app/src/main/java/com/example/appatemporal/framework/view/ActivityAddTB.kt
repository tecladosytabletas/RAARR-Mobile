package com.example.appatemporal.framework.view

import android.R
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivityAddCategoriaBinding
import com.example.appatemporal.databinding.ActivityAddTipoBoletoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.GetEventTBFilterViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_tipo_boleto.*

/**
 * This file is linked with activity_add_tipo_boleto.xml
 * This file is in charge of controlling the logic behind the funtion add a type of ticket
 *
 * @see activity_add_tipo_boleto.xml
 *
 * @author Resendiz & Camalich
 *
 * */

class ActivityAddTB : AppCompatActivity() {
    private val viewModel: GetEventTBFilterViewModel by viewModels()
    private lateinit var binding: ActivityAddTipoBoletoBinding
    private  var auth = FirebaseAuth.getInstance()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTipoBoletoBinding.inflate(layoutInflater)
        val btn=binding.btnTB
        val tb=binding.SpinnerTB
        val precio=binding.precioBoleto
        val cantidad=binding.maxBoletos
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

        val idEvent = intent.getStringExtra("idEvent")
        val eid = idEvent.toString()
        val repository = Repository(this)
        viewModel.getTBFilter(eid, repository)

        viewModel.dropdownList.observe(this, androidx.lifecycle.Observer {
            Log.d("dropdown list log", it.toString())
            val TBString = arrayListOf<String>()
            for(name in it){
                TBString.add("${name}")
            }
            val myadapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item,TBString)
            myadapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.SpinnerTB.adapter = myadapter
        })

            btn.setOnClickListener {
                if(SpinnerTB.getCount()==0){
                    Toast.makeText(applicationContext, "No existen tipos de boleto disponibles para tu evento.", Toast.LENGTH_SHORT).show()
                    btn.isClickable=false
                }
                else{
                    if((cantidad.text.toString().isNotEmpty())&&(precio.text.toString().isNotEmpty())){
                        viewModel.AddEventoTipoBoleto(eid,tb.getSelectedItem().toString(), precio.text.toString().toInt(), cantidad.text.toString().toInt(),repository)
                        var allAreaNames=arrayListOf<String>()
                        allAreaNames.clear()
                        val AreaAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, allAreaNames)
                        AreaAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                        binding.SpinnerTB.adapter = AreaAdapter
                        val submitBtn =  Intent(this, ActivityMisEventosOrganizador::class.java)
                        this.startActivity(submitBtn)
                    }
                    else{
                        Toast.makeText(applicationContext, "Llena todos los campos antes de continuar.", Toast.LENGTH_SHORT).show()
                    }


                }
            }
    }
}