package com.example.appatemporal.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appatemporal.data.GetUserDataRequirement
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.domain.models.UserModel
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val userData = MutableLiveData<UserModel>()
    val getUserDataRequirement = GetUserDataRequirement()

    fun getUser(uid: String, repository: Repository) {
        viewModelScope.launch {
            val userInfo = getUserDataRequirement(uid, repository).data
            val user = UserModel(userInfo?.get("nombre_Usuario").toString(), userInfo?.get("apellidos_Usuario").toString(),
                userInfo?.get("email").toString(), userInfo?.get("fecha_Nacimiento").toString(), userInfo?.get("genero").toString())
            userData.postValue(user)
        }
    }

}