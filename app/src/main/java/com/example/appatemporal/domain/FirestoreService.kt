package com.example.appatemporal.domain

import android.util.Log
import com.example.appatemporal.domain.models.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private val db = Firebase.firestore

    fun addUser(uid: String, user: UserModel) {
        db.collection("Usuario")
            .document(uid)
            .set(user)
            .addOnSuccessListener {
                Log.d("FirestoreLogs","Added User Correctly")
            }
            .addOnFailureListener {
                Log.d("FirestoreLogs","Added user failed, exception: $it")
            }
    }

    suspend fun verifyUser (uid: String) : Boolean{
        var userExists: Boolean = false
        db.collection("Usuario")
            .document(uid)
            .get()
            .addOnSuccessListener { documentRef ->
                if (documentRef.exists()) {
                    userExists = true
                }
            }
            .await()
        return userExists
    }
}