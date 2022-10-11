package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.AddUserLocalDBRequirement
import com.example.appatemporal.data.RegisterUserRequirement
import com.example.appatemporal.data.localdatabase.entities.Usuario
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.UserModel
import kotlinx.coroutines.launch

/**
 * Class that inherits from ViewModel
 */
class RegisterUserViewModel : ViewModel() {

    val registerUserRequirement = RegisterUserRequirement()
    val addUserLocalDBRequirement = AddUserLocalDBRequirement()

    /**
     * Adds user to Firestore database
     *
     * @param uid: String -> User Uid
     * @param user: UserModel -> Model to insert documents to Firestore
     * @param role: String -> Role selected by the user
     * @param repository: Repository -> Repository of the application
     */
    fun addUser(uid: String ,user: UserModel, role: String, repository: Repository) {
        viewModelScope.launch {
            registerUserRequirement(uid, user, role, repository)
        }
    }

    /**
     * Adds user to local database
     *
     * @param user: Usuario -> Entity to insert the user data to the local database
     * @param repository: Repository -> Repository of the application
     */
    fun addUserLocalDB(user: Usuario, repository: Repository) {
        viewModelScope.launch {
            addUserLocalDBRequirement(user, repository)
        }
    }
}