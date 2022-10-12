package com.example.appatemporal.framework.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.AddUserLocalDBRequirement
import com.example.appatemporal.data.GetUserDataRequirement
import com.example.appatemporal.data.GetUserRoleRequirement
import com.example.appatemporal.data.ValidateUserExistenceRequirement
import com.example.appatemporal.data.localdatabase.entities.Usuario
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.UserModel
import kotlinx.coroutines.launch

/**
 * Class that inherits from ViewModel
 */
class OTPViewModel : ViewModel() {
    val userExists = MutableLiveData<Boolean>()
    val userData = MutableLiveData<Usuario>()

    private val getUserDataRequirement = GetUserDataRequirement()
    private val getUserRoleRequirement = GetUserRoleRequirement()
    private val addUserLocalDBRequirement = AddUserLocalDBRequirement()

    /**
     * Verifies if the user exists in Firestore database
     *
     * @param uid: String -> User Uid
     * @param repository: Repository -> Repository of the application
     */
    fun verifyUser(uid: String, repository: Repository) {
        viewModelScope.launch {
            val validateUserExistenceRequirement = ValidateUserExistenceRequirement()
            var existence = validateUserExistenceRequirement(uid, repository)
            userExists.postValue(existence)
        }
    }

    /**
     * Adds user information to local database
     *
     * @param user: Usuario -> Model of Usuario with user information
     * @param repository: Repository -> Repository of the application
     */
    fun addUserLocalDB(user: Usuario, repository: Repository) {
        viewModelScope.launch {
            addUserLocalDBRequirement(user, repository)
        }
    }

    /**
     * Gets user information
     *
     * @param uid: String -> User uid
     * @param repository: Repository -> Repository of the application
     */
    fun getUser(uid: String, repository: Repository) {
        viewModelScope.launch {
            val userInfo = getUserDataRequirement(uid, repository).data
            val role = getUserRoleRequirement(uid, repository).data
            val user = Usuario(uid, userInfo?.get("nombre").toString(), userInfo?.get("apellidos").toString(),
                userInfo?.get("email").toString(), userInfo?.get("fecha_nacimiento").toString(), userInfo?.get("genero").toString(), role?.get("nombre").toString())
            userData.postValue(user)
        }
    }
}