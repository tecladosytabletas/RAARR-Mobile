package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import com.example.appatemporal.data.RegisterUserRequirement
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.UserModel

class RegisterUserViewModel : ViewModel() {

    val registerUserRequirement = RegisterUserRequirement()

    fun addUser(uid: String ,user: UserModel, repository: Repository) {
        registerUserRequirement(uid, user, repository)
    }

}