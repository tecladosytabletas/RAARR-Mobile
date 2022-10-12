package com.example.appatemporal.framework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appatemporal.R
import com.example.appatemporal.databinding.AddActivitiesBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.adapters.ActividadAdapter
import com.example.appatemporal.framework.viewModel.DeleteActivityViewModel
import kotlinx.android.synthetic.main.add_activities.*
import java.util.*

class DeleteActivity : AppCompatActivity(){
    private val  viewModel : DeleteActivityViewModel by viewModels()
    private lateinit var binding: AddActivitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Define global variables that will be used in other functions
        var secondTipo = ""
        var tipo = ""

        //Define the context and keep it on a variable
        val repository = Repository(this)

        //Extract parameters that came on the intent that is used to access this interface
        var myExtras :Bundle? = intent.extras
        var idProyecto: Int=  myExtras?.getInt("id_proyecto")?:-1

        val doneActivities =  viewModel.countDoneActivities(repository, idProyecto, "Completado")
        val totalActivities = viewModel.countAllActivities(repository, idProyecto)


        /*
        * Update the attribute of "estatus" of a project if the number of activities
        * that have the attribute "completado" is the same as the number of all activities
        * registered in the project.
        */

        if (doneActivities == totalActivities && doneActivities !=0 && totalActivities !=0){
            viewModel.updateEstatusCompletado(true, idProyecto, repository)
        }
        else{
            viewModel.updateEstatusCompletado(false, idProyecto, repository)
        }

        //Extract the array with the elements that will use the spinner that contains
        //the options to filter the tasks
        val filterList = resources.getStringArray(R.array.filterList)
        //Adapter that will be used to create the drop button effect
        val arrayAdapter1 = ArrayAdapter(this, R.layout.dropdown_menu, filterList)
        //Extract the element that will be used to as a drop button
        val autocompleteTV1 = findViewById<AutoCompleteTextView>(R.id.spinnerFilterMain)
        //Initialize the drop button effects of material design
        autocompleteTV1.setAdapter(arrayAdapter1)


        //Define the component that will bu used like a spinner
        val autocompleteTV2 = findViewById<AutoCompleteTextView>(R.id.spinnerFilterToFilter)

        //Extract the array list that contains the options of area
        val areaList = resources.getStringArray(R.array.areaList)
        //Adapter that will be used to create the drop button effect
        val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_menu, areaList)

        //Extract the array list that contains the options of estatus
        val estatusList = resources.getStringArray(R.array.estatusList)
        //Adapter that will be used to create the drop button effect
        val arrayAdapter3 = ArrayAdapter(this, R.layout.dropdown_menu, estatusList)

        //Extract the array list that contains the options of prioridad
        val prioridadList = resources.getStringArray(R.array.prioridadList)
        //Adapter that will be used to create the drop button effect
        val arrayAdapter4 = ArrayAdapter(this, R.layout.dropdown_menu, prioridadList)

        //Extract an empty list
        val allList = resources.getStringArray(R.array.allList)
        //Adapter that will be used to create the drop button effect
        val arrayAdapter5 = ArrayAdapter(this, R.layout.dropdown_menu, allList)

        /*
        * Function used to change the options on the spinnerFiltertoFilter according to
        * the selection of an option on spinnerMain
        */
        binding.spinnerFilterMain.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (binding.spinnerFilterMain.getText().toString()=="Area"){
                    //Erase the text on the spinner
                    binding.spinnerFilterToFilter.setText("")
                    //Extract the options assigned to Area
                    autocompleteTV2.setAdapter(arrayAdapter2)

                    /*
                    * Function used to make the dynamic the display of the activities.
                    * According to the option that the user selects on the spinnerFilterToFilter
                    * the recycler view will be updated with the activities that
                    * has the same attribute of the selection.
                    */

                    binding.spinnerFilterToFilter.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable) {
                            if (binding.spinnerFilterToFilter.getText().toString()=="Negocios"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                            else if (binding.spinnerFilterToFilter.getText().toString()=="Personales"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                            else if (binding.spinnerFilterToFilter.getText().toString()=="Compras"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                            else if (binding.spinnerFilterToFilter.getText().toString()=="Mercancias"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                        }

                        override fun beforeTextChanged(s: CharSequence, start: Int,
                                                       count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence, start: Int,
                                                   before: Int, count: Int) {}
                    })

                }
                else if (binding.spinnerFilterMain.getText().toString()=="Estatus"){
                    //Erase the text on the spinner
                    binding.spinnerFilterToFilter.setText("")
                    //Extract the options assigned to Estatus
                    autocompleteTV2.setAdapter(arrayAdapter3)

                    /*
                    * Function used to make the dynamic the display of the activities.
                    * According to the option that the user selects on the spinnerFilterToFilter
                    * the recycler view will be updated with the activities that
                    * has the same attribute of the selection.
                    */
                    binding.spinnerFilterToFilter.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable) {
                            if (binding.spinnerFilterToFilter.getText().toString()=="En Proceso"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                            else if (binding.spinnerFilterToFilter.getText().toString()=="No Completado"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                            else if (binding.spinnerFilterToFilter.getText().toString()=="Completado"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                        }
                        override fun beforeTextChanged(s: CharSequence, start: Int,
                                                       count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence, start: Int,
                                                   before: Int, count: Int) {}
                    })
                }

                else if (binding.spinnerFilterMain.getText().toString()=="Prioridad"){
                    //Erase the text on the spinner
                    binding.spinnerFilterToFilter.setText("")
                    //Extract the options assigned to Prioridad
                    autocompleteTV2.setAdapter(arrayAdapter4)

                    /*
                    * Function used to make dynamic the display of the activities.
                    * According to the option that the user selects on the spinnerFilterToFilter
                    * the recycler view will be updated with the activities that
                    * has the same attribute of the selection.
                    */
                    binding.spinnerFilterToFilter.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable) {
                            if (binding.spinnerFilterToFilter.getText().toString()=="Alta"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                            else if (binding.spinnerFilterToFilter.getText().toString()=="Media"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }
                            else if (binding.spinnerFilterToFilter.getText().toString()=="Baja"){
                                secondTipo  = binding.spinnerFilterToFilter.getText().toString()
                                tipo = binding.spinnerFilterMain.getText().toString()
                                //Call the function to display activities in the recycler view
                                initRecyclerView(repository, idProyecto, secondTipo, tipo)
                            }

                        }

                        override fun beforeTextChanged(s: CharSequence, start: Int,
                                                       count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence, start: Int,
                                                   before: Int, count: Int) {}
                    })
                }
                else if (binding.spinnerFilterMain.getText().toString()=="Todos"){
                    //Erase the text on the spinner
                    binding.spinnerFilterToFilter.setText("")
                    //Extract the options assigned to Todos which is an empty list
                    autocompleteTV2.setAdapter(arrayAdapter5)
                    secondTipo  = ""
                    tipo = binding.spinnerFilterMain.getText().toString()
                    //Call the function to display activities in the recycler view
                    initRecyclerView(repository, idProyecto, secondTipo, tipo)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {}
        })

        //Call the function to display activities in the recycler view
        initRecyclerView(repository, idProyecto, secondTipo, tipo)

        //Button that will be used to initialize the function to add an activity of a project
        binding.newTaskButton.setOnClickListener {
            val intent = Intent(this, AddNewActivityForm::class.java)
            with(intent){
                putExtra("id_proyecto", idProyecto)
            }
            startActivity(intent)
        }

        // ----------------------------Navbar------------------------------------
        val userRole = getSharedPreferences("user", Context.MODE_PRIVATE).getString("rol", "").toString()

        // Visibility
        if (userRole != "Organizador") {
            binding.navbar.budgetIcon.visibility = android.view.View.GONE
            binding.navbar.metricsIcon.visibility = android.view.View.GONE
            binding.navbar.budgetText.visibility = android.view.View.GONE
            binding.navbar.metricsText.visibility = android.view.View.GONE
        }
        if (userRole == "Ayudante") {
            binding.navbar.eventsIcon.visibility = android.view.View.GONE
            binding.navbar.eventsText.visibility = android.view.View.GONE
        }

        // Intents
        binding.navbar.homeIcon.setOnClickListener {
            if(userRole == "Organizador"){
                val intent = Intent(this, ActivityMainHomepageOrganizador::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, ActivityMainHomepageEspectador::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.eventsIcon.setOnClickListener {
            if(userRole == "Organizador"){
                val intent = Intent(this,ActivityMisEventosOrganizador::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this,CategoriasEventos::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.budgetIcon.setOnClickListener {
            val intent = Intent(this, ProyectoOrganizador::class.java)
            startActivity(intent)
        }

        binding.navbar.ticketsIcon.setOnClickListener {
            if (userRole == "Espectador" || userRole == "Organizador") {
                val intent = Intent(this, BoletoPorEventoActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, RegisterQRView::class.java)
                startActivity(intent)
            }
        }

        binding.navbar.metricsIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        // ----------------------------Navbar------------------------------------
    }

    /*
    * Function used to extract the activities that are in the database
    * according to the texts of the spinners
    *
    *@param repository - The context used in this interface
    *@param id - The id of the project the user selected before getting into this interface
    *@param SecondTipo - The text extracted of the spinnerFilterToFilter
    *@param Tipo - The text extracted from the spinnerFilterMain
    */
    private fun initRecyclerView( repository: Repository,id: Int, SecondTipo: String, tipo:String) {

        if (tipo == "Area"){
            viewModel.getAllActivitiesArea(id,SecondTipo, repository)
        }
        else if (tipo == "Estatus"){
            viewModel.getAllActivitiesEstatus(id,SecondTipo, repository)
        }
        else if (tipo == "Prioridad"){
            viewModel.getAllActivitiesPrioridad(id,SecondTipo, repository)
        }
        else{
            viewModel.getAllActivitiesid(id,repository)
        }
        viewModel.activities.observe(this, Observer { activityList ->
            if (activityList.isEmpty()){
                Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
            }
            binding.todoRv.layoutManager = LinearLayoutManager(this)
            binding.todoRv.adapter = ActividadAdapter(activityList, viewModel)
        })
    }

}