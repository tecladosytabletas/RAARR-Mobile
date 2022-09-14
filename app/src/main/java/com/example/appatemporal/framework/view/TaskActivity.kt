package com.example.appatemporal.framework.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appatemporal.R
import com.example.appatemporal.data.localdatabase.LocalDatabase
import com.example.appatemporal.data.localdatabase.entities.Actividad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.appatemporal.databinding.ActivityTaskBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.viewModel.TaskActivityViewModel
import java.lang.Integer.parseInt

class TaskActivity : AppCompatActivity() {

    private val viewModel: TaskActivityViewModel by viewModels()
    private lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository(this)
        binding.saveBtn.setOnClickListener{
            var timestamp = System.currentTimeMillis()
            val actividad = Actividad(
                0,
                1,
                binding.textViewActNombre.text.toString(),
                binding.textViewActArea.text.toString(),
                binding.textViewActEstatus.text.toString(),
                timestamp.toString(),
                ""
            )
            lifecycleScope.launch{
                viewModel.addActivity(actividad, repository)
            }
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }


    }




}
