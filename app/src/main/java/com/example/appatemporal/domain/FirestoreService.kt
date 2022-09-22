package com.example.appatemporal.domain

import android.util.Log
import com.example.appatemporal.domain.models.UserModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
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
                .whereEqualTo("id_Usuario", uid)
                .get()
                .await()
        return events.count()
    }

    suspend fun ventasCount(uid : String) : Pair<Int, Int> {
        var ventasCount : Int = 0
        var asistenciasCount : Int = 0
        var ventas : QuerySnapshot =
            db.collection("Usuario_Evento")
                .whereEqualTo("id_Usuario", uid)
                .get()
                .await()
        for (document in ventas){
            var funciones : QuerySnapshot =
                db.collection("Funcion")
                    .whereEqualTo("id_Evento",document.data?.get("id_Evento"))
                    .get()
                    .await()
            for (document in funciones){
                var boletosAuxVentas : QuerySnapshot =
                    db.collection("Boleto")
                        .whereEqualTo("id_Funcion", document.id)
                        .get()
                        .await()
                ventasCount += boletosAuxVentas.count()
            }
            for (document in funciones){
                var boletosAuxAsistencias : QuerySnapshot =
                    db.collection("Boleto")
                        .whereEqualTo("id_Funcion", document.id)
                        .whereEqualTo("activo", false)
                        .get()
                        .await()
                asistenciasCount += boletosAuxAsistencias.count()
            }
        }
        val result = Pair(ventasCount, asistenciasCount)
        return result
    }

    suspend fun getRating(uid: String) : Float {
        var acumulado = 0f
        var count = 0f
        var events : QuerySnapshot =
            db.collection("Usuario_Evento")
                .whereEqualTo("id_Usuario", uid)
                .get()
                .await()
        for (document in events){
            var feedbacks : QuerySnapshot =
                db.collection("Feedback")
                .whereEqualTo("id_Evento",document.data?.get("id_Evento"))
                .get()
                .await()
            for (document in feedbacks){
                acumulado += document.data?.get("rating").toString().toInt()
                count += 1
            }
        }
        if (count <= 0) return 0f
        return (acumulado/count).toFloat()
    }

    suspend fun getRevenue(uid: String) : Int {
        var ventaTotal = 0
        var boletos: QuerySnapshot
        var tiposBoleto: QuerySnapshot
        var events: QuerySnapshot =
            db.collection("Usuario_Evento")
                .whereEqualTo("id_Usuario", uid)
                .get()
                .await()
        for (document in events) {
            var funciones: QuerySnapshot =
                db.collection("Funcion")
                    .whereEqualTo("id_Evento", document.data?.get("id_Evento"))
                    .get()
                    .await()
            for (document in funciones) {
                boletos =
                    db.collection("Boleto")
                        .whereEqualTo("id_Funcion", document.id)
                        .get()
                        .await()
                Log.d("LOG boletos", boletos.count().toString())
                tiposBoleto =
                    db.collection("Evento_Tipo_Boleto")
                        .whereEqualTo("id_Evento", document.data?.get("id_Evento"))
                        .get()
                        .await()
                for (tipoBoleto in tiposBoleto) {
                    for (document in boletos) {
                        if (document.data?.get("id_Tipo_Boleto") == tipoBoleto.data?.get("id_Tipo_Boleto")) {
                            Log.d("IF de los boletos", tipoBoleto.data?.get("precio").toString())
                            ventaTotal += tipoBoleto.data?.get("precio").toString().toInt()
                        }
                        Log.d("LOG for boletos", document.id.toString())
                    }
                }
            }
        }
        return ventaTotal
    }

    suspend fun updateTicketValue(resulted: String): Boolean {
        var result: String = resulted

        var exito: Boolean = false

        var Queryresult: Boolean = true

        db.collection("Boleto")
            .whereEqualTo("hash_QR", result)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Queryresult = document.getField<Boolean>("activo") as Boolean
                    if (Queryresult == true) {
                        db.collection("Boleto").document(document.id).update("activo", false)
                        exito = true
                    } else {
                        exito = false
                    }
                }
            }
            .await()

        return exito
    }

}