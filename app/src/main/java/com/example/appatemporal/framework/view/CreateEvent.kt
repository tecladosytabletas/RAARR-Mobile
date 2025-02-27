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
import com.example.appatemporal.databinding.ActivityCrearEventoBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.CreateEventModel
import com.example.appatemporal.domain.models.EventModel
import com.example.appatemporal.domain.models.EventoTipoBoletoModel
import com.example.appatemporal.domain.models.FunctionModel
import com.example.appatemporal.framework.viewModel.AddNewEventViewModel
import com.example.appatemporal.framework.viewModel.GetEventCategoryViewModel
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.text.SimpleDateFormat


class CreateEvent :AppCompatActivity() {
    private val viewModel: AddNewEventViewModel by viewModels()
    private val viewModelCategory: GetEventCategoryViewModel by viewModels()
    private var auth = FirebaseAuth.getInstance()

    private lateinit var binding: ActivityCrearEventoBinding
    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearEventoBinding.inflate(layoutInflater)
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
        val nombre = binding.NombreEvento
        val descripcion = binding.DescripcionEvento
        val ciudad = binding.CiudadEvento
        val estado = binding.EstadoEvento
        val ubicacion = binding.UbicacionEvento
        val direccion = binding.DireccionEvento
        val longitud = binding.LongitudEvento
        val latitud = binding.LatitudEvento
        val foto = binding.UrlImagenEvento
        val video = binding.URLVideoEvento
        val datePickerF = binding.datePicker1
        val horaInicio = binding.timePickerInicio
        val horaFin = binding.timePickerFin
        val artista= binding.ArtistaEvento
        val submit = binding.submitBtn
        val precio=binding.precioBoletoNormal
        val cantidad=binding.maxBoletosNormales
        val categoria = binding.SpinnerCategoria
        val repository = Repository(this)
        val categorias = viewModelCategory.getcategoria(repository)

        horaInicio.setIs24HourView(true);
        horaFin.setIs24HourView(true);
        val today = Calendar.getInstance()
        datePickerF.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month/$year"
        }

        viewModelCategory.dropdownList.observe(this, androidx.lifecycle.Observer {
            Log.d("dropdown list log", it.toString())
            val categoryString = arrayListOf<String>()
            for(name in it){
                categoryString.add("${name}")
            }
            val myadapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item,categoryString)
            myadapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.SpinnerCategoria.adapter = myadapter
        })

        submit.setOnClickListener {
            if((nombre.text.toString().isNotEmpty())&&(descripcion.text.toString().isNotEmpty())&&(ciudad.text.toString().isNotEmpty())&&(estado.text.toString().isNotEmpty())&&(ubicacion.text.toString().isNotEmpty())&&(direccion.text.toString().isNotEmpty())&&(longitud.text.toString().isNotEmpty())&&(latitud.text.toString().isNotEmpty())&&(foto.text.toString().isNotEmpty())&&(video.text.toString().isNotEmpty())&&(cantidad.text.toString().isNotEmpty())&&(precio.text.toString().isNotEmpty())&&(artista.text.toString().isNotEmpty())){
                Log.d("El nombre del evento es ", "Kiubo" + nombre.text.toString())
                val activo = 1
                val divisa = "Pesos"
                val current = LocalDateTime.now()
                val formattedDate: String = current.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                val fecha_Creado = formattedDate
                val fecha_modificado = formattedDate
                val evento = CreateEventModel(nombre.text.toString(), descripcion.text.toString(),ciudad.text.toString(),estado.text.toString(), ubicacion.text.toString(),direccion.text.toString(),longitud.text.toString(),latitud.text.toString(),foto.text.toString(),video.text.toString(),activo,0,divisa,fecha_Creado,fecha_modificado)
                Log.d("El último ticket es:", evento.ciudad)
                //val artista = findViewById<EditText>(R.id.Artista_Evento)


                val hourI = horaInicio.hour
                val minuteI = horaInicio.minute
                val hourF = horaFin.hour
                val minuteF = horaFin.minute
                val hoursI = if (hourI < 10) "0" + hourI else hourI
                val minI = if (minuteI < 10) "0" + minuteI else minuteI
                val hora_stringI="$hoursI:$minI"

                val hoursF = if (hourF < 10) "0" + hourF else hourF
                val minF = if (minuteF < 10) "0" + minuteF else minuteF
                val hora_stringF="$hoursF:$minF"

                val year = datePickerF.year
                var monti = datePickerF.month
                val day = datePickerF.dayOfMonth
                monti = monti + 1

                val monthF = if (monti < 10) "0" + monti else monti
                val dayF = if (day < 10) "0" + day else day


                val fecha="$dayF/$monthF/$year"
                val funcion=FunctionModel(fecha,hora_stringI,hora_stringF)
                val boletos=EventoTipoBoletoModel("","General",cantidad.text.toString().toInt(),precio.text.toString().toInt())
                val userUid = getSharedPreferences("userUid", Context.MODE_PRIVATE)
                    .getString("userUid", "").toString()

                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val currentI = LocalDate.now()
                val formattedDateI: String = currentI.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                val firstDate: Date = sdf.parse(fecha)
                val secondDate: Date = sdf.parse(formattedDateI)

                val strI = fecha+" "+hora_stringI
                val strF = fecha+" "+hora_stringF
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                val dateTimeI = LocalDateTime.parse(strI, formatter)
                val dateTimeF = LocalDateTime.parse(strF, formatter)
                val cmp = firstDate.compareTo(secondDate)
                val tmp = dateTimeI.compareTo(dateTimeF)

                if((cmp > 0)&&(tmp < 0)){
                    viewModel.AddEvent(evento, repository, artista.text.toString(),funcion, userUid, boletos, categoria.getSelectedItem().toString())
                    val submitBtn =  Intent(this, ActivityMisEventosOrganizador::class.java)
                    this.startActivity(submitBtn)
                }
                else{
                    Toast.makeText(applicationContext, "La fecha u hora elegida es inválida. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(applicationContext, "Llena todos los campos antes de continuar.", Toast.LENGTH_SHORT).show()
            }



        }


    }
}