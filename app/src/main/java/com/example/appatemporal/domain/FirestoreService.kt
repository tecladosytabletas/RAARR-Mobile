package com.example.appatemporal.domain

import android.util.Log
import com.example.appatemporal.domain.models.UserModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
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

    suspend fun addUserRole(uid: String, role: String) {
        val dbRole = db.collection("Rol")
            .whereEqualTo("nombre_Rol", role)
            .get()
            .addOnSuccessListener {
                Log.d("FirestoreLogs","Got Role Correctly: ${it.documents[0].id}")
            }.await()

        val userRole = hashMapOf(
            "id_Usuario" to uid,
            "id_Rol" to dbRole.documents[0].id
        )

        db.collection("Usuario_Rol")
            .add(userRole)
            .addOnSuccessListener {
                Log.d("FirestoreLogs","Added User wih Role Correctly")
            }
            .addOnFailureListener {
                Log.d("FirestoreLogs","Added user failed, exception: $it")
            }
    }

    suspend fun verifyUser(uid: String) : Boolean {
        var userExists = false
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

    suspend fun getUser(uid: String) : DocumentSnapshot {
        var userData: DocumentSnapshot =
            db.collection("Usuario")
            .document(uid)
            .get()
            .await()
        return userData
    }

    suspend fun getUserRole(uid: String) : DocumentSnapshot {
        var dbRole: QuerySnapshot =
            db.collection("Usuario_Rol")
                .whereEqualTo("id_Usuario", uid)
                .get()
                .await()
        var userRole: DocumentSnapshot =
            db.collection("Rol")
                .document(dbRole.documents[0].data?.get("id_Rol").toString())
                .get()
                .await()
        return userRole
    }

    suspend fun eventCount(uid: String) : Int {
        var events : QuerySnapshot =
            db.collection("Usuario_Evento")
                .whereEqualTo("Id_Usuario", uid)
                .get()
                .await()
        return events.count()
    }

    suspend fun ventasCount(uid : String) : Pair<Int, Int> {
        var ventasCount : Int = 0
        var asistenciasCount: Int = 0
        var ventas: QuerySnapshot =
            db.collection("Usuario_Evento")
                .whereEqualTo("Id_Usuario", uid)
                .get()
                .await()
        for (document in ventas){
            var funciones: QuerySnapshot =
                db.collection("Funcion")
                    .whereEqualTo("Id_Evento",document.data?.get("Id_Evento").toString())
                    .get()
                    .await()
            for (document in funciones){
                var boletosAuxVentas: QuerySnapshot =
                    db.collection("Boleto")
                        .whereEqualTo("Id_Funcion", document.data?.get("Id_Funcion").toString())
                        .get()
                        .await()
                ventasCount += boletosAuxVentas.count()
            }
            for (document in funciones){
                var boletosAuxAsistencias: QuerySnapshot =
                    db.collection("Boleto")
                        .whereEqualTo("Id_Funcion", document.data?.get("Id_Funcion").toString())
                        .whereEqualTo("Activo", false)
                        .get()
                        .await()
                asistenciasCount += boletosAuxAsistencias.count()
            }
        }
        val result = Pair(ventasCount, asistenciasCount)
        return result
    }
}