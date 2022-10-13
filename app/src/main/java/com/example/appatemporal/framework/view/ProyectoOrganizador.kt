package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.databinding.ProyectosOrganizadorBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ProjectsAdapter
import com.example.appatemporal.framework.viewModel.ProyectoOrganizadorViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ProyectoOrganizador : AppCompatActivity() {
    // Initialize the view model
    private val  viewModel : ProyectoOrganizadorViewModel by viewModels()
    private var auth = FirebaseAuth.getInstance()
    // Initialize the binding with the xml file
    private lateinit var binding: ProyectosOrganizadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProyectosOrganizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)

        // Function used to display the projects that has the status of completed
        binding.tvCompletedProject.setOnClickListener{
            binding.tvCompletedProject.getBackground().setAlpha(70);
            binding.tvNoCompletedProject.getBackground().setAlpha(255);
            viewModel.getAllProjectsCompleted(true,repository)
            viewModel.projects.observe(this, Observer { projectList ->
                Log.d("Prueba", projectList.toString())
                binding.recyclerViewProjects.layoutManager = LinearLayoutManager(this)
                binding.recyclerViewProjects.adapter = ProjectsAdapter(projectList, viewModel)
            })
        }

        // Function used to display the projects that has the status of pending
        binding.tvNoCompletedProject.setOnClickListener{
            binding.tvNoCompletedProject.getBackground().setAlpha(70);
            binding.tvCompletedProject.getBackground().setAlpha(255);
            viewModel.getAllProjectsCompleted(false,repository)
            viewModel.projects.observe(this, Observer { projectList1 ->
                Log.d("Prueba", projectList1.toString())
                binding.recyclerViewProjects.layoutManager = LinearLayoutManager(this)
                binding.recyclerViewProjects.adapter = ProjectsAdapter(projectList1, viewModel)
            })
        }

        // Elements used to display all the projects that are stored in the database
        viewModel.getProjects(repository)
        viewModel.projects.observe(this, Observer { projectList ->
            if (projectList.isEmpty()) {
                Toast.makeText(this, "no se encontraron resultados", Toast.LENGTH_SHORT).show()
            }
            Log.d("Prueba", projectList.toString())
            binding.recyclerViewProjects.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewProjects.adapter = ProjectsAdapter(projectList, viewModel)
        })
        // Function that will be initialized once the element ivEditIcon is clicked by the user
        // The function inside it is an intent to the AddNewProjectForm view
        binding.tvNewProject.setOnClickListener {
            val intent = Intent(this, AddNewProjectForm::class.java)
            startActivity(intent)
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