package com.example.appatemporal.framework.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.ActivityRegisterBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.UserModel
import com.example.appatemporal.framework.viewModel.RegisterUserViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = Repository(this)
        val registerUserViewModel: RegisterUserViewModel by viewModels()

        val userUid: String = intent.getStringExtra("userUid").toString()

        binding.registerBtn.setOnClickListener {
            if (!binding.editnameReg2.text.isNullOrEmpty() && !binding.editlnameReg2.text.isNullOrEmpty()
                && !binding.editemailReg2.text.isNullOrEmpty() && !binding.editDateReg2.text.isNullOrEmpty()
                && !binding.editGenderReg2.text.isNullOrEmpty()) {

                val user = UserModel(binding.editnameReg2.text.toString(), binding.editlnameReg2.text.toString(),
                    binding.editemailReg2.text.toString(), binding.editDateReg2.text.toString(), binding.editGenderReg2.text.toString())
                registerUserViewModel.addUser(userUid, user, repository)
                val intent = Intent(this, Main::class.java)
                intent.putExtra("userUid", userUid)
                startActivity(intent)
            }
        }
    }
}