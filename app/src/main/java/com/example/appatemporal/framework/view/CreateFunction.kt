package com.example.appatemporal.framework.view


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.R
import com.example.appatemporal.databinding.ActivityAddArtistaBinding
import com.example.appatemporal.databinding.ActivityCreateFunctionBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.AddNewEventViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_function.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
/**
 * This file is linked with activity_create_function.xml
 * This file is in charge of controlling the logic behind the funtion add a function to an event
 *
 * @see activity_create_function.xml
 *
 * @author Resendiz & Camalich
 *
 * */
class CreateFunction : AppCompatActivity() {

    private lateinit var binding: ActivityCreateFunctionBinding
    private val viewModel: AddNewEventViewModel by viewModels()
    private var auth = FirebaseAuth.getInstance()
    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateFunctionBinding.inflate(layoutInflater)
        val datePickerF = binding.datePicker1
        val horaInicio = binding.timePickerInicio
        val horaFin = binding.timePickerFin
        val btn = binding.submitBtn
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
        val myExtras : Bundle? = intent.extras

        val idEvent = intent.getStringExtra("idEvent")

        horaInicio.setIs24HourView(true);
        horaFin.setIs24HourView(true);

        val today = Calendar.getInstance()
        datePickerF.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month/$year"
            Toast.makeText(this@CreateFunction, msg, Toast.LENGTH_SHORT).show()
        }

        btn.setOnClickListener{
            val hourI = horaInicio.hour
            val minuteI = horaInicio.minute
            val hourF = horaFin.hour
            val minuteF = horaFin.minute
            val repository = Repository(this)
            val hoursI = if (hourI < 10) "0" + hourI else hourI
            val minI = if (minuteI < 10) "0" + minuteI else minuteI
            val hora_stringI="$hoursI:$minI"

            val hoursF = if (hourF < 10) "0" + hourF else hourF
            val minF = if (minuteF < 10) "0" + minuteF else minuteF
            val hora_stringF="$hoursF:$minF"

            val year = datePickerF.year
            var montI = datePickerF.month
            val day = datePickerF.dayOfMonth
            montI = montI + 1

            val monthF = if (montI < 10) "0" + montI else montI
            val dayF = if (day < 10) "0" + day else day


            val fecha="$dayF/$monthF/$year"

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val currentI = LocalDate.now()
            val formattedDateI: String = currentI.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            val firstDate: Date = sdf.parse(fecha)
            val secondDate: Date = sdf.parse(formattedDateI)
            val eid = idEvent.toString()
            val strI = fecha+" "+hora_stringI
            val strF = fecha+" "+hora_stringF
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            val dateTimeI = LocalDateTime.parse(strI, formatter)
            val dateTimeF = LocalDateTime.parse(strF, formatter)
            val cmp = firstDate.compareTo(secondDate)
            val tmp = dateTimeI.compareTo(dateTimeF)

            if((cmp > 0)&&(tmp < 0)){
            viewModel.AddFunction(eid,repository,fecha,hora_stringI,hora_stringF)
                val submitBtn =  Intent(this, ActivityMisEventosOrganizador::class.java)
                this.startActivity(submitBtn)
        }
            else{
                Toast.makeText(applicationContext, "La fecha elegida es inválida. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show()
            }

    }
}
}