package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivityProyectoOrganizadorBinding
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import com.google.firebase.auth.FirebaseAuth

class ActivityProyectoOrganizador: AppCompatActivity() {
    // Initialize the view model
    private val viewModel: ProyectoOrganizadorViewModel by viewModels()
    // Initialize the binding with the xml file
    private lateinit var binding: ActivityProyectoOrganizadorBinding
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProyectoOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var myExtras :Bundle? = intent.extras
        //Counts and displays the amount of activitys with status Incompleted or in progress
        val stringToDo = "Actividades por completar: ".plus(myExtras?.getInt("pendingActivities"))
        //Counts and displays the amount of activitys with status completed
        val stringCompleted = "Actividades completadas: ".plus(myExtras?.getInt("doneActivities"))

        binding.activitiesCompleted.text = stringToDo
        binding.activitiesToDo.text = stringCompleted
        //Sets the project information on screen
        binding.line2.text = myExtras?.getString("fecha_inicio")
        binding.projectName.text = myExtras?.getString("nombre_proyecto")
        var idProyecto:Int =  myExtras?.getInt("id_proyecto")?:-1
        var ganancia:Double = (myExtras?.getDouble("ganancia_proyecto")?:-1) as Double
        var presupuesto:Double = (myExtras?.getDouble("presupuesto_proyecto")?:-1) as Double
        var meta:Double = (myExtras?.getDouble("meta_proyecto")?:-1) as Double



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


        binding.bottomObjective.setOnClickListener {

            val intent = Intent(this, PresupuestoAndMeta::class.java)
            with(intent){
                putExtra("id_proyecto", idProyecto)
                putExtra("gananciaK", ganancia)
                putExtra("presupuestoK", presupuesto)
                putExtra("metaK", meta)
            }
            startActivity(intent)
        }
        binding.bottomActivity.setOnClickListener {

            val intent = Intent(this, DeleteActivity::class.java)
            with(intent){
                putExtra("id_proyecto", idProyecto)
            }
            Log.d("prueba", idProyecto.toString())
            startActivity(intent)
        }

        binding.buttonCostos.setOnClickListener{
            val intent = Intent(this, DeleteCosto::class.java)
            with(intent){
                putExtra("id_proyecto", idProyecto)
            }
            startActivity(intent)
        }
    }
}
